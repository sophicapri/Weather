package com.example.weather.di

import com.example.weather.data.WeatherRepositoryImpl
import com.example.weather.data.WeatherService
import com.example.weather.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository = WeatherRepositoryImpl(weatherService)
}