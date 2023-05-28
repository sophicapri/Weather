package com.example.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.surface,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TopAppBar(
                            title = { Text(text = "Weather") },
                        )

                        val viewModel by viewModels<HomeViewModel>()
                        val uiState by remember { viewModel.uiState }.collectAsState()
                        HomeScreen(
                            state = uiState,
                            events = viewModel.uiEvents,
                            radioButtonsValues = viewModel.radioButtons,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherTheme {
        HomeScreen(
            state = UiResult.Success(
                HomeViewModel.UiState(
                    CurrentWeather(
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
                        degType = "",
                        image = ""
                    )
                )
            ),
            radioButtonsValues = arrayOf(),
            events = HomeViewModel.UiEvents(
                onSearchClick = {},
                onInputChange = {},
                onClearInput = {},
                onRadioButtonSelected = {}
            )
        )
    }
}