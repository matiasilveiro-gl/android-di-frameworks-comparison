package com.matias.di

import com.matias.data.service.MoviesRequestGenerator
import com.matias.data.service.MoviesServiceImpl
import com.matias.data.service.api.MoviesApi
import com.matias.domain.service.MoviesService
import org.koin.dsl.module

object DataModule {
    val repositoriesModule = module {
        single { MoviesRequestGenerator() }
        single<MoviesApi> {
            get<MoviesRequestGenerator>().createService(MoviesApi::class.java)
        }
        single<MoviesService> { MoviesServiceImpl(get()) }
    }
}
