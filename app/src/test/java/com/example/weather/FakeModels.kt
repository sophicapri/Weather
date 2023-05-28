package com.example.weather

import com.example.weather.data.model.CurrentWeatherDto
import com.example.weather.data.model.ForecastWeatherDto
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather

class FakeModels {
    companion object {
        fun getForecastDto() =
            ForecastWeatherDto(
                low = "",
                high = "",
                skyCodeDay = "",
                skyTextDay = "",
                date = "",
                day = "",
                shortDay = "",
                precip = "",
                degType = ""
            )

        fun getCurrentWeatherDto() =
            CurrentWeatherDto(
                temperature = "",
                skyCode = "",
                skyText = "",
                date = "",
                observationTime = "",
                observationPoint = "",
                feelsLike = "",
                humidity = "",
                windDisplay = "",
                day = "",
                shortDay = "",
                windSpeed = "",
                degType = ""
            )

        fun getForecast() =
            ForecastWeather(
                low = "",
                high = "",
                skyTextDay = "",
                shortDay = "",
                degType = "",
                image = ""
            )

        fun getCurrentWeather() =
            CurrentWeather(
                temperature = "",
                skyText = "",
                observationTime = "",
                observationPoint = "",
                feelsLike = "",
                humidity = "",
                windDisplay = "",
                day = "",
                degType = "",
                image = ""
            )
    }
}