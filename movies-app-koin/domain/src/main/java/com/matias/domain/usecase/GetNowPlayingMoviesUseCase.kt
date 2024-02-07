package com.matias.domain.usecase

import com.matias.domain.entity.Movie
import com.matias.domain.service.MoviesService
import com.matias.domain.util.ResultOf

interface GetNowPlayingMoviesUseCase {
    suspend operator fun invoke(): ResultOf<List<Movie>>
}

class GetNowPlayingMoviesUseCaseImpl(
    private val moviesService: MoviesService
) : GetNowPlayingMoviesUseCase {

    override suspend operator fun invoke(): ResultOf<List<Movie>> {
        return when (val result = moviesService.getNowPlayingMovies()) {
            is ResultOf.Success -> {
                // TODO: Insert in local DB
                // TODO: Return from local db
                result
            }
            is ResultOf.Failure -> {
                // TODO: Fetch from local db
                ResultOf.Failure(Exception("Local db not yet implemented"))
            }
        }
    }
}
