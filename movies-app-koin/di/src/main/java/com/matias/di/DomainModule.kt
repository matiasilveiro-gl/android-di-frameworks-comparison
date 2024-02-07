package com.matias.di

import com.matias.domain.usecase.GetMovieByIdUseCase
import com.matias.domain.usecase.GetMovieByIdUseCaseImpl
import com.matias.domain.usecase.GetNowPlayingMoviesUseCase
import com.matias.domain.usecase.GetNowPlayingMoviesUseCaseImpl
import org.koin.dsl.module

object DomainModule {
    val useCasesModule = module {
        includes(DataModule.repositoriesModule)

        single<GetMovieByIdUseCase> { GetMovieByIdUseCaseImpl(get()) }
        single<GetNowPlayingMoviesUseCase> { GetNowPlayingMoviesUseCaseImpl(get()) }
    }
}
