package com.matias.domain.usecase

import com.matias.domain.entity.MovieDetails
import com.matias.domain.service.MoviesService
import com.matias.domain.util.ResultOf

interface GetMovieByIdUseCase {
    suspend operator fun invoke(id: Int): ResultOf<MovieDetails>
}

class GetMovieByIdUseCaseImpl(
    private val moviesService: MoviesService
) : GetMovieByIdUseCase {

    override suspend operator fun invoke(id: Int): ResultOf<MovieDetails> {
        return when (val result = moviesService.getMovieById(id)) {
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
