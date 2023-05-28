package com.example.weather.data

import com.example.weather.FakeModels
import com.example.weather.domain.WeatherRepository
import com.example.weather.domain.model.ForecastWeather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImplTest {
    private lateinit var repository: WeatherRepository
    private val weatherService = mockk<WeatherService>()
    private val degreeType = "C"

    @Before
    fun setUp() {
        repository = WeatherRepositoryImpl(weatherService)
    }

    @Test
    fun `Given a location When I request the forecast Then I receive a list of Domain Forecast`() =
        runBlocking() {
            val location = "Brasil"
            val expected = listOf(FakeModels.getForecastDto())
            coEvery { weatherService.getForecast(location, degreeType) } returns expected

            val result: Result<List<ForecastWeather>> = repository.getForecast(location)

            assert(result.getOrNull()?.get(0) == expected.toDomain()[0])
        }

    @Test
    fun `Given a location When I request the current weather Then I receive the corresponding weather`() =
        runBlocking {
            val location = "Sweden"
            val expected = FakeModels.getCurrentWeatherDto()
            coEvery {
                weatherService.getCurrent(
                    location,
                    degreeType
                )
            } returns expected

            val result = repository.getCurrent(location)

            assert(result.getOrNull() == expected.toDomain())
        }

    @Test
    fun `When the request for the current weather returns an exception Then I receive a failing result`() =
        runBlocking() {
            val location = "l"
            coEvery { weatherService.getCurrent(location, degreeType) } throws Exception()

            val result: Result<List<ForecastWeather>> = repository.getForecast(location)

            assert(result.isFailure)
        }

    @Test
    fun `When the request for the forecast returns an exception Then I receive a failing result`() =
        runBlocking {
            val location = "Lzz"
            coEvery { weatherService.getForecast(location, degreeType) } throws Exception()

            val result: Result<List<ForecastWeather>> = repository.getForecast(location)

            assert(result.isFailure)
        }
}