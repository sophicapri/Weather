package com.example.weather.data

import com.example.weather.domain.WeatherRepository
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather

class WeatherRepositoryImpl(private val weatherService: WeatherService) :
    WeatherRepository {

    override suspend fun getForecast(location: String): Result<List<ForecastWeather>> {
        return try {
            Result.success(weatherService.getForecast(location, getDegreeType()).toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrent(location: String): Result<CurrentWeather> {
        return try {
            Result.success(weatherService.getCurrent(location, getDegreeType()).toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getDegreeType() = "C"
}
