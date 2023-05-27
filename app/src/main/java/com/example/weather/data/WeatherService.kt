package com.example.weather.data

import com.example.weather.data.model.CurrentWeatherDto
import com.example.weather.data.model.ForecastWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast?deg=C")
    suspend fun getForecast(@Query("loc") location: String): List<ForecastWeatherDto>

    @GET("current?deg=C")
    suspend fun getCurrentWeather(@Query("loc") location: String): CurrentWeatherDto
}