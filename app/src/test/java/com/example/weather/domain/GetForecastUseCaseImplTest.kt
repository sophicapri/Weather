package com.example.weather.domain

import com.example.weather.FakeModels
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetForecastUseCaseImplTest {
    lateinit var getForecastUseCase: GetForecastUseCase
    private val weatherRepository = mockk<WeatherRepository>()

    @Before
    fun setUp() {
        getForecastUseCase = GetForecastUseCaseImpl(weatherRepository)
    }

    @Test
    fun `GIVEN a location WHEN I request the current weather THEN the repository returns the current weather`() =
        runBlocking {
            val location = "Paris"
            val expected = FakeModels.getForecast()

            coEvery { weatherRepository.getForecast(location) } returns Result.success(
                listOf(expected)
            )

            val result = getForecastUseCase(location)

            assertEquals(expected, result.getOrNull()?.get(0))
        }
}