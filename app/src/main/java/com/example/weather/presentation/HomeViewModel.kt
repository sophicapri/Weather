package com.example.weather.presentation

import androidx.lifecycle.ViewModel
import com.example.weather.di.IODispatcher
import com.example.weather.di.MainDispatcher
import com.example.weather.domain.GetCurrentWeatherUseCase
import com.example.weather.domain.GetForecastUseCase
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @IODispatcher ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(job + ioDispatcher)

    private var location = ""
    private var weatherType = WeatherTypeEnum.CURRENT

    private var _state: MutableStateFlow<UiResult<UiState>> = MutableStateFlow(UiResult.None())
    val uiState: StateFlow<UiResult<UiState>> = _state

    val uiEvents = UiEvents(
        onSearchClick = { onSearchClick() },
        onInputChange = { onInputChange(text = it) },
        onClearInput = { onClearInput() },
        onRadioButtonSelected = { onRadioButtonSelected(weatherType = it) }
    )

    val radioButtons = WeatherTypeEnum.values()

    private fun onInputChange(text: String) {
        location = text
    }

    private fun onRadioButtonSelected(weatherType: WeatherTypeEnum) {
        this.weatherType = weatherType
    }

    private fun onSearchClick() {
        if (location.isNotEmpty()) {
            location = location.trim()
            _state.value = UiResult.Loading()
            when (weatherType) {
                WeatherTypeEnum.CURRENT -> displayCurrentWeather()
                WeatherTypeEnum.FORECAST -> displayForecast()
            }
        }
    }

    private fun onClearInput() {
        location = ""
        _state.value = UiResult.None()
    }

    private fun displayCurrentWeather() {
        ioScope.launch {
            val res = getCurrentWeatherUseCase(location)
            withContext(mainDispatcher) {
                if (res.isSuccess)
                    _state.value = UiResult.Success(UiState(current = res.getOrNull()))
                else
                    _state.value = UiResult.Failure()
            }
        }
    }

    private fun displayForecast() {
        ioScope.launch {
            val res = getForecastUseCase(location)
            withContext(mainDispatcher) {
                if (res.isSuccess)
                    _state.value = UiResult.Success(UiState(forecast = res.getOrNull()))
                else
                    _state.value = UiResult.Failure()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        ioScope.cancel()
    }

    data class UiState(
        val current: CurrentWeather? = null,
        val forecast: List<ForecastWeather>? = null,
        val error: String? = null,
    )

    data class UiEvents(
        val onSearchClick: () -> Unit,
        val onInputChange: (String) -> Unit,
        val onClearInput: () -> Unit,
        val onRadioButtonSelected: (WeatherTypeEnum) -> Unit,
    )
}