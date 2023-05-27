package com.example.weather.domain.model

data class ForecastWeather(
    val low: String,
    val high: String,
    val skyCodeDay: String,
    val skyTextDay: String,
    val date: String,
    val day: String,
    val shortDay: String,
    val precip: String,
    val degType: String,
    val image: String,
)
