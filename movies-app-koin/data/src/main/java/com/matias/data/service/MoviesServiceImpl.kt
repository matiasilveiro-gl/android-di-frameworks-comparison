package com.matias.data.service

import android.util.Log
import com.matias.data.service.api.MoviesApi
import com.matias.data.service.mapper.mapToMovieDetailsDomain
import com.matias.data.service.mapper.mapToMovieDomain
import com.matias.domain.entity.Movie
import com.matias.domain.entity.MovieDetails
import com.matias.domain.entity.error.NoNetworkException
import com.matias.domain.entity.error.NotAuthorizedException
import com.matias.domain.entity.error.NotFoundException
import com.matias.domain.service.MoviesService
import com.matias.domain.util.ResultOf
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class MoviesServiceImpl(
    private val api: MoviesApi
) : MoviesService {

    companion object {
        const val TAG = "MoviesService"
    }

    override suspend fun getMovieById(id: Int): ResultOf<MovieDetails> {
        return withErrorHandling {
            val response = api.getMovieById(id)
            if (response.isSuccessful) {
                response.body()?.let { return ResultOf.Success(it.mapToMovieDetailsDomain()) }
            }
            throw Exception(response.message())
        }
    }

    override suspend fun getNowPlayingMovies(): ResultOf<List<Movie>> {
        return withErrorHandling {
            val response = api.getNowPlayingMovies()
            if (response.isSuccessful) {
                response.body()?.let { resp ->
                    return ResultOf.Success(resp.results.map { it.mapToMovieDomain() })
                }
            }
            throw Exception(response.message())
        }
    }

    private inline fun <T : Any> withErrorHandling(block: () -> T): ResultOf<T> {
        return try {
            ResultOf.Success(block())
        } catch (e: IOException) {
            Log.e(TAG, "Exception thrown: $e")
            ResultOf.Failure(NoNetworkException())
        } catch (e: HttpException) {
            Log.e(TAG, "Exception thrown: $e")
            when (e.code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> ResultOf.Failure(NotAuthorizedException())
                HttpURLConnection.HTTP_NOT_FOUND -> ResultOf.Failure(NotFoundException())
                else -> ResultOf.Failure(e)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception thrown: $e")
            ResultOf.Failure(e)
        }
    }
}
