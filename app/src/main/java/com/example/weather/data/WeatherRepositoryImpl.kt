package com.example.weather.data

import com.example.weather.domain.api.WeatherRepository
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather

class WeatherRepositoryImpl(private val weatherService: WeatherService) :
    WeatherRepository {

    override suspend fun getForecast(location: String): List<ForecastWeather> {
        return weatherService.getForecast(location, getDegreeType()).map {
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
                image =  "http://blob.weather.microsoft.com/static/weather4/en-us/law/${it.skyCodeDay}.gif",
            )
        }
    }

    override suspend fun getCurrent(location: String): CurrentWeather {
        return weatherService.getCurrent(location, getDegreeType()).let {
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
    }

    // if we want to let the user change the degree type in the future,
    // we will only have to change this function and instead retrieve the selected degree type (through DataStore Preferences for example)
    private fun getDegreeType() = "C"

}
