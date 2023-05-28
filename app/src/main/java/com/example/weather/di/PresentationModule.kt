package com.example.weather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers



@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideMainDispatcher() : CoroutineDispatcher = Dispatchers.Main
}