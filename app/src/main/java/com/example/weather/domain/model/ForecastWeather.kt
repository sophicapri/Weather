package com.example.weather.domain.model

data class ForecastWeather(
    val low: String,
    val high: String,
    val skyTextDay: String,
    val day: String,
    val shortDay: String,
    val degType: String,
    val image: String,
)
