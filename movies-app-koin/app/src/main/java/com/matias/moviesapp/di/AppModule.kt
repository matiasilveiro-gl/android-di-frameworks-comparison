package com.matias.moviesapp.di

import com.matias.di.DomainModule
import org.koin.dsl.module

object AppModule {
    val appModules = module {
        includes(
            DomainModule.useCasesModule,
            ViewModelModule.module
        )
    }
}
