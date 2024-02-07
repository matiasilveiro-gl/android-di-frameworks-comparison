package com.matias.di

import com.matias.data.service.MoviesRequestGenerator
import com.matias.data.service.MoviesServiceImpl
import com.matias.data.service.api.MoviesApi
import com.matias.domain.service.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideMoviesRequestGenerator(): MoviesRequestGenerator = MoviesRequestGenerator()

    @Provides
    @Singleton
    fun provideMoviesApi(
        moviesRequestGenerator: MoviesRequestGenerator
    ): MoviesApi = moviesRequestGenerator.createService(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideMoviesService(
        moviesApi: MoviesApi
    ): MoviesService = MoviesServiceImpl(moviesApi)
}
