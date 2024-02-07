package com.matias.moviesapp

import android.app.Application

class MainApplication : Application() {

    // Instance of AppContainer that will be used by all the Activities of the app
    val appContainer = AppContainer()
}
