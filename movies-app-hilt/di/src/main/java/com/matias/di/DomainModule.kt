package com.matias.di

import com.matias.domain.service.MoviesService
import com.matias.domain.usecase.GetMovieByIdUseCase
import com.matias.domain.usecase.GetMovieByIdUseCaseImpl
import com.matias.domain.usecase.GetNowPlayingMoviesUseCase
import com.matias.domain.usecase.GetNowPlayingMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetMovieByIdUseCase(
        moviesService: MoviesService
    ): GetMovieByIdUseCase = GetMovieByIdUseCaseImpl(moviesService)

    @Provides
    fun provideGetNowPlayingMoviesUseCase(
        moviesService: MoviesService
    ): GetNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCaseImpl(moviesService)
}
