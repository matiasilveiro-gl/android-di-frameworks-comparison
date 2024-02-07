package com.matias.moviesapp

import android.app.Application
import com.matias.moviesapp.di.DaggerApplicationComponent

class MainApplication : Application() {
    val appComponent = DaggerApplicationComponent.create()
}
