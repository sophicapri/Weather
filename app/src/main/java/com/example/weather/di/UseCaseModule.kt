package com.example.weather.di

import com.example.weather.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCurrentWeatherUseCase(weatherRepository: WeatherRepository): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCaseImpl(weatherRepository)
    }

    @Provides
    fun provideGetForecastUseCase(weatherRepository: WeatherRepository): GetForecastUseCase {
        return GetForecastUseCaseImpl(weatherRepository)
    }
}