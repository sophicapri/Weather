package com.example.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.GetCurrentWeatherUseCase
import com.example.weather.domain.GetForecastUseCase
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
) : ViewModel() {
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
        if (location.isNotEmpty())
            when (weatherType) {
                WeatherTypeEnum.CURRENT -> displayCurrentCurrentWeather()
                WeatherTypeEnum.FORECAST -> displayForecast()
            }
    }

    private fun onClearInput() {
        _state.value = UiResult.None()
    }

    private fun displayCurrentCurrentWeather() {
        viewModelScope.launch {
            val res = getCurrentWeatherUseCase(location)
            if (res.isSuccess)
                _state.value = UiResult.Success(UiState(current = res.getOrNull()))
            else
                _state.value = UiResult.Failure()
        }
    }

    private fun displayForecast() {
        viewModelScope.launch {
            val res = getForecastUseCase(location)
            if (res.isSuccess)
                _state.value = UiResult.Success(UiState(forecast = res.getOrNull()))
            else
                _state.value = UiResult.Failure()
        }
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