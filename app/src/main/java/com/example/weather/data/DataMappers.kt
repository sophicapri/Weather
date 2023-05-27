package com.example.weather.data

import com.example.weather.data.model.CurrentWeatherDto
import com.example.weather.data.model.ForecastWeatherDto
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather

fun List<ForecastWeatherDto>.toDomain() = map {
    ForecastWeather(
        low = it.low,
        high = it.high,
        skyCodeDay = it.skyCodeDay,
        skyTextDay = it.skyTextDay,
        date = it.date,
        day = it.day,
        shortDay = it.shortDay,
        precip = it.precip,
        degType = it.degType,
        image = "http://blob.weather.microsoft.com/static/weather4/en-us/law/${it.skyCodeDay}.gif",
    )
}

fun CurrentWeatherDto.toDomain() = let {
    CurrentWeather(
        temperature = it.temperature,
        skyCode = it.skyCode,
        skyText = it.skyText,
        date = it.date,
        observationTime = it.observationTime,
        observationPoint = it.observationPoint,
        feelsLike = it.feelsLike,
        humidity = it.humidity,
        windDisplay = it.windDisplay,
        day = it.day,
        shortDay = it.shortDay,
        windSpeed = it.windSpeed,
        degType = it.degType,
        image = "http://blob.weather.microsoft.com/static/weather4/en-us/law/${it.skyCode}.gif",
    )
}