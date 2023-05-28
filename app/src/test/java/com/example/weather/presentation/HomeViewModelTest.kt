package com.example.weather.presentation

import com.example.weather.FakeModels
import com.example.weather.domain.GetCurrentWeatherUseCase
import com.example.weather.domain.GetForecastUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
    lateinit var viewModel: HomeViewModel
    private val getCurrentWeatherUseCase = mockk<GetCurrentWeatherUseCase>()
    private val getForecastUseCase = mockk<GetForecastUseCase>()
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        viewModel = HomeViewModel(getCurrentWeatherUseCase, getForecastUseCase, testDispatcher)
    }

    @Test
    fun `Given the selection of the forecast button WHEN clicking the search button, I get the forecast`() =
        runBlocking {
            val location = "Paris"
            val expected = FakeModels.getForecast()

            coEvery { getForecastUseCase(location) } returns Result.success(listOf(expected))

            viewModel.uiEvents.onInputChange(location)
            viewModel.uiEvents.onRadioButtonSelected(WeatherTypeEnum.FORECAST)
            viewModel.uiEvents.onSearchClick()

            coVerify(exactly = 1) { getForecastUseCase(location) }
        }

    @Test
    fun `Given the selection of the current button WHEN clicking the search button, I get the current weather`() =
        runBlocking {
            val location = "Paris"
            val expected = FakeModels.getCurrentWeather()

            coEvery { getCurrentWeatherUseCase(location) } returns Result.success(expected)

            viewModel.uiEvents.onInputChange(location)
            viewModel.uiEvents.onSearchClick()

            coVerify(exactly = 1) { getCurrentWeatherUseCase(location) }
        }
}