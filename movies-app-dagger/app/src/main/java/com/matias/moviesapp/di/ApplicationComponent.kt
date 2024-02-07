package com.matias.moviesapp.di

import com.matias.di.DomainModule
import com.matias.moviesapp.ui.activity.MainActivity
import com.matias.moviesapp.ui.fragment.MovieDetailsFragment
import com.matias.moviesapp.ui.fragment.NowPlayingFragment
import dagger.Component

@Component(
    modules = [
        DomainModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {
    // Inject into activities
    fun inject(activity: MainActivity)

    // Inject into fragments
    fun inject(fragment: NowPlayingFragment)
    fun inject(fragment: MovieDetailsFragment)
}
