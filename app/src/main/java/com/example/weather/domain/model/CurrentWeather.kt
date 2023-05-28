package com.example.weather.domain.model

data class CurrentWeather(
    val temperature: String,
    val skyText: String,
    val observationTime: String,
    val observationPoint: String,
    val feelsLike: String,
    val humidity: String,
    val windDisplay: String,
    val day: String,
    val shortDay: String,
    val degType: String,
    val image: String
)
