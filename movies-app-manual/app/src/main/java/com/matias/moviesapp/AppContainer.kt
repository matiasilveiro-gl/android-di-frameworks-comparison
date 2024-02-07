package com.matias.moviesapp

import com.matias.data.service.MoviesRequestGenerator
import com.matias.data.service.MoviesServiceImpl
import com.matias.data.service.api.MoviesApi
import com.matias.domain.service.MoviesService
import com.matias.domain.usecase.GetMovieByIdUseCase
import com.matias.domain.usecase.GetMovieByIdUseCaseImpl
import com.matias.domain.usecase.GetNowPlayingMoviesUseCase
import com.matias.domain.usecase.GetNowPlayingMoviesUseCaseImpl

class AppContainer {

    // / Data layer
    private val moviesApi: MoviesApi = MoviesRequestGenerator().createService(MoviesApi::class.java)
    private val moviesService: MoviesService = MoviesServiceImpl(moviesApi)

    // Domain layer - UseCases
    val getMovieByIdUseCase: GetMovieByIdUseCase = GetMovieByIdUseCaseImpl(moviesService)
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCaseImpl(moviesService)
}
