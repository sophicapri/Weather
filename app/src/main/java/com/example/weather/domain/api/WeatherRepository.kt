package com.example.weather.domain.api

import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather

interface WeatherRepository {
    suspend fun getForecast(location: String): List<ForecastWeather>

    suspend fun getCurrent(location: String): CurrentWeather
}