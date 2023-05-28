package com.example.weather.presentation

import android.os.Build.VERSION.SDK_INT
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.ForecastWeather

@Composable
fun HomeScreen(
    state: UiResult<HomeViewModel.UiState>,
    events: HomeViewModel.UiEvents,
    radioButtonsValues: Array<WeatherTypeEnum>,
) {
    Column(
        modifier = Modifier.padding(
            start = 24.dp,
            end = 24.dp
        )
    ) {
        SearchLocationView(radioButtonsValues, events)

        if (state is UiResult.Success) {
            state.data.current?.let {
                CurrentWeatherView(it)
            }

            state.data.forecast?.let {
                ForecastView(it)
            }
        } else if (state is UiResult.Failure) {
            Toast.makeText(LocalContext.current, "Oops", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun SearchLocationView(
    radioButtonsValues: Array<WeatherTypeEnum>,
    events: HomeViewModel.UiEvents
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var value by remember { mutableStateOf("") }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                top = 16.dp,
            )
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                tint = TextFieldDefaults
                    .textFieldColors()
                    .leadingIconColor(enabled = true, isError = false).value,
                contentDescription = "Clear",
                modifier = Modifier.padding(start = 16.dp)
            )

            TextField(
                value = value,
                onValueChange = { newValue: String ->
                    value = newValue
                    events.onInputChange(newValue)
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        modifier = Modifier.clickable {
                            value = ""
                            events.onClearInput()
                        }
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent),
                textStyle = MaterialTheme.typography.body1.copy(color = Color.Black),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            var selectedOption by remember {
                mutableStateOf(
                    radioButtonsValues[0]
                )
            }

            radioButtonsValues.forEach {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = selectedOption == it,
                        onClick = {
                            selectedOption = it
                            events.onRadioButtonSelected(it)
                        }
                    )

                    Text(
                        text = when (it) {
                            WeatherTypeEnum.CURRENT -> it.name.lowercase() // todo: change to res
                            WeatherTypeEnum.FORECAST -> it.name.lowercase() // todo: change to res
                        },
                        color = Color.Black
                    )
                }
            }
        }

        TextButton(
            onClick = events.onSearchClick,
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.LightGray,
                contentColor = Color.Black
            ),
        ) {
            Text(
                "SEARCH", Modifier.padding(start = 6.dp, end = 6.dp),
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun CurrentWeatherView(currentWeather: CurrentWeather) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = currentWeather.observationPoint)
        Row {
            Text(text = currentWeather.day, modifier = Modifier.padding(end = 8.dp))
            Text(text = currentWeather.observationTime)
        }
        Text(text = currentWeather.skyText)

        Row {
            RemoteGif(
                url = currentWeather.image,
                size = 40.dp,
                contentDesc = "Weather icon"
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ForecastView(forecastWeather: List<ForecastWeather>) {
    FlowRow {
        forecastWeather.forEach {
            ForecastItem(it)
        }
    }
}

@Composable
private fun ForecastItem(forecastWeather: ForecastWeather) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 8.dp, end = 4.dp)
    ) {
        Text(
            text = forecastWeather.shortDay,
            color = MaterialTheme.colors.onBackground
        )

        RemoteGif(
            url = forecastWeather.image,
            size = 50.dp,
            contentDesc = forecastWeather.skyTextDay
        )

        Row {
            Text(
                text = "${forecastWeather.low}°${forecastWeather.degType}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 4.dp),
                color = Color.Black
            )
            Text(
                text = "${forecastWeather.high}°${forecastWeather.degType}",
            )
        }
    }
}

@Composable
private fun RemoteGif(url: String, size: Dp, contentDesc: String) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .build(),
        imageLoader = imageLoader,
        modifier = Modifier.size(size),
        contentDescription = contentDesc
    )
}
