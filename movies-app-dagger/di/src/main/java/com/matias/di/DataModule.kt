package com.matias.di

import com.matias.data.service.MoviesRequestGenerator
import com.matias.data.service.MoviesServiceImpl
import com.matias.data.service.api.MoviesApi
import com.matias.domain.service.MoviesService
import dagger.Module
import dagger.Provides

@Module
object DataModule {
    @Provides
    fun provideMoviesRequestGenerator(): MoviesRequestGenerator = MoviesRequestGenerator()

    @Provides
    fun provideMoviesApi(
        moviesRequestGenerator: MoviesRequestGenerator
    ): MoviesApi = moviesRequestGenerator.createService(MoviesApi::class.java)

    @Provides
    fun provideMoviesService(
        moviesApi: MoviesApi
    ): MoviesService = MoviesServiceImpl(moviesApi)
}
