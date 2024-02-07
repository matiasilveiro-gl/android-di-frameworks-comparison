package com.matias.moviesapp.di

import com.matias.moviesapp.viewmodel.MovieDetailsViewModel
import com.matias.moviesapp.viewmodel.NowPlayingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object ViewModelModule {
    val module = module {
        viewModelOf(::NowPlayingViewModel)
        viewModelOf(::MovieDetailsViewModel)
    }
}
