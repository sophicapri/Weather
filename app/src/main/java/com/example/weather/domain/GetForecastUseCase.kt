package com.example.weather.domain

import com.example.weather.domain.model.ForecastWeather

interface GetForecastUseCase {
     suspend operator fun invoke(location: String) : Result<List<ForecastWeather>>
}

class GetForecastUseCaseImpl(private val weatherRepository: WeatherRepository) : GetForecastUseCase {
     override suspend fun invoke(location: String): Result<List<ForecastWeather>> {
          return weatherRepository.getForecast(location)
     }
}