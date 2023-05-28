package com.example.weather.domain

import com.example.weather.FakeModels
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetCurrentWeatherUseCaseImplTest {
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private val weatherRepository = mockk<WeatherRepository>()

    @Before
    fun setUp() {
        getCurrentWeatherUseCase = GetCurrentWeatherUseCaseImpl(weatherRepository)
    }

    @Test
    fun `GIVEN a location WHEN I request the current weather THEN the repository returns the current weather`() =
        runBlocking {
            val location = "Paris"
            val expected = FakeModels.getCurrentWeather()

            coEvery { weatherRepository.getCurrent(location) } returns Result.success(expected)

            val result = getCurrentWeatherUseCase(location)

            assertEquals(expected, result.getOrNull())
        }
}