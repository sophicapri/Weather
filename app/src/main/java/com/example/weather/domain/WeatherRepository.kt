package com.example.weather.domain

import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather

interface WeatherRepository {
    suspend fun getForecast(location: String): Result<List<ForecastWeather>>

    suspend fun getCurrent(location: String): Result<CurrentWeather>
}