package com.example.weather.domain

import com.example.weather.domain.model.CurrentWeather

interface GetCurrentWeatherUseCase {
     suspend operator fun invoke(location: String) : Result<CurrentWeather>
}

class GetCurrentWeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : GetCurrentWeatherUseCase {
     override suspend fun invoke(location: String): Result<CurrentWeather> {
          return weatherRepository.getCurrent(location)
     }
}