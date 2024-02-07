package com.matias.moviesapp.di

import com.matias.di.DataModule
import com.matias.di.DomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DataModule::class,
        DomainModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class AppModule
