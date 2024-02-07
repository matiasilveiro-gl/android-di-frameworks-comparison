package com.matias.data.service.api

import com.matias.data.service.response.MovieDetailsResponse
import com.matias.data.service.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    @GET("/3/movie/{movieId}")
    suspend fun getMovieById(@Path("movieId") id: Int): Response<MovieDetailsResponse>

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<MovieListResponse>
}
