package com.example.weather.domain.model

data class CurrentWeather(
    val temperature: String,
    val skyCode: String,
    val skyText: String,
    val date: String,
    val observationTime: String,
    val observationPoint: String,
    val feelsLike: String,
    val humidity: String,
    val windDisplay: String,
    val day: String,
    val shortDay: String,
    val windSpeed: String,
    val degType: String,
    val image: String
)
