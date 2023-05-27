package com.example.weather.data

import com.example.weather.data.model.CurrentWeatherDto
import com.example.weather.data.model.ForecastWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast")
    suspend fun getForecast(@Query("loc") location: String, @Query("deg") deg: String): List<ForecastWeatherDto>

    @GET("current")
    suspend fun getCurrent(@Query("loc") location: String, @Query("deg") deg: String): CurrentWeatherDto
}