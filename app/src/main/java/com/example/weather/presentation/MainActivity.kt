package com.example.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                            title = {
                                Text(
                                    text = "Weather",
                                    color = MaterialTheme.colors.onPrimary
                                )
                            },
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