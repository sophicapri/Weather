package com.example.weather.data.model

import com.squareup.moshi.Json

data class ForecastWeatherDto(
    val low: String,

    val high: String,

    @Json(name = "skycodeday")
    val skyCodeDay: String,

    @Json(name = "skytextday")
    val skyTextDay: String,

    val date: String,

    val day: String,

    @Json(name = "shortday")
    val shortDay: String,

    val precip: String,

    val degType: String
)
