package com.example.weather.data

import com.example.weather.data.model.CurrentWeatherDto
import com.example.weather.data.model.ForecastWeatherDto
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather

fun List<ForecastWeatherDto>.toDomain() = map {
    ForecastWeather(
        low = it.low,
        high = it.high,
        skyTextDay = it.skyTextDay,
        shortDay = it.shortDay,
        degType = it.degType,
        image = "https://blob.weather.microsoft.com/static/weather4/en-us/law/${it.skyCodeDay}.gif",
    )
}

fun CurrentWeatherDto.toDomain() = let {
    CurrentWeather(
        temperature = it.temperature,
        skyText = it.skyText,
        observationTime = it.observationTime,
        observationPoint = it.observationPoint,
        feelsLike = it.feelsLike,
        humidity = it.humidity,
        windDisplay = it.windDisplay,
        day = it.day,
        degType = it.degType,
        image = "https://blob.weather.microsoft.com/static/weather4/en-us/law/${it.skyCode}.gif",
    )
}
