package com.example.weather.data.model

import com.squareup.moshi.Json

data class CurrentWeatherDto(
    val temperature: String,

    @Json(name = "skycode")
    val skyCode: String,

    @Json(name = "skytext")
    val skyText: String,

    val date: String,

    @Json(name = "observationtime")
    val observationTime: String,

    @Json(name = "observationpoint")
    val observationPoint: String,

    @Json(name = "feelslike")
    val feelsLike: String,

    val humidity: String,

    @Json(name = "winddisplay")
    val windDisplay: String,

    val day: String,

    @Json(name = "shortday")
    val shortDay: String,

    @Json(name = "windspeed")
    val windSpeed: String,

    val degType: String
)
