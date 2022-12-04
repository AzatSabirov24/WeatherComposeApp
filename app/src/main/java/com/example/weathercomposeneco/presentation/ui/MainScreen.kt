package com.example.weathercomposeneco.presentation.ui

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercomposeneco.R
import com.example.weathercomposeneco.presentation.WeatherViewModel
import com.example.weathercomposeneco.presentation.ui.theme.BlueDark
import com.example.weathercomposeneco.presentation.ui.theme.WhiteMain

@Composable
fun MainScreen(
    data: @Composable () -> Unit,
    viewModel: WeatherViewModel,
    activity: Activity
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(
                id = R.drawable.weather_bg
            ),
            contentDescription = "app background",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.85f),
            contentScale = ContentScale.FillBounds
        )
        viewModel.state.apply {
            when {
                isLoading -> LoadingWeather(
                    text = stringResource(
                        id = R.string.loading_weather
                    )
                )
                weatherInfo != null -> data()
                isUpdate -> LoadingWeather(
                    text = stringResource(
                        id = R.string.update_weather
                    )
                )
                error != null -> {
                    ErrorDialog(
                        viewModel = viewModel,
                        activity = activity
                    )
                    Button(
                        onClick = {
                            if (viewModel.state.error != null)
                                Toast.makeText(
                                    activity,
                                    "Нужен доступ к геолокации",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            viewModel.fetchWeather(isUpdate = false)
                        },
                        modifier = Modifier.align(Alignment.Center),
                        content = {
                            Text(
                                text = stringResource(id = R.string.to_update_weather),
                                color = WhiteMain,
                                fontSize = 24.sp
                            )
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = BlueDark),
                        elevation = null
                    )
                }
            }
        }
    }
}

@Composable
fun DataPortrait(viewModel: WeatherViewModel) {
    Column {
        WeatherCard(
            isPortrait = true,
            viewModel = viewModel
        )
    }
}

@Composable
fun DataLandscape(viewModel: WeatherViewModel) {
    Row {
        WeatherCard(
            isPortrait = false,
            viewModel = viewModel
        )
    }
}

@Composable
fun WeatherCard(
    isPortrait: Boolean,
    viewModel: WeatherViewModel
) {
    if (isPortrait) {
        MainCard(
            state = viewModel.state,
            viewModel = viewModel,
            isPortrait = true
        )
        Column(
            modifier = Modifier.verticalScroll(
                state = ScrollState(0)
            )
        ) {
            WeatherForecastCard(
                state = viewModel.state,
                dayIndex = 0,
                day = stringResource(R.string.today)
            )
            WeatherForecastCard(
                state = viewModel.state,
                dayIndex = 1,
                day = stringResource(R.string.tomorrow)
            )
            WeatherForecastCard(
                state = viewModel.state,
                dayIndex = 2,
                day = stringResource(R.string.day_after_tomorrow)
            )
        }
    } else {
        Row {
            MainCard(
                Modifier.weight(0.4f),
                state = viewModel.state,
                viewModel = viewModel,
                isPortrait = false
            )
            Column(
                modifier = Modifier
                    .verticalScroll(
                        state = ScrollState(0)
                    )
                    .weight(0.6f)
            ) {
                WeatherForecastCard(
                    state = viewModel.state,
                    dayIndex = 0,
                    day = stringResource(R.string.today)
                )
                WeatherForecastCard(
                    state = viewModel.state,
                    dayIndex = 1,
                    day = stringResource(R.string.tomorrow)
                )
                WeatherForecastCard(
                    state = viewModel.state,
                    dayIndex = 2,
                    day = stringResource(R.string.day_after_tomorrow)
                )
            }
        }
    }
}

@Composable
fun LoadingWeather(
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 80.dp,
                    bottom = 60.dp
                ),
            text = text,
            fontSize = 48.sp,
            color = BlueDark,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.padding(
                top = 48.dp
            )
        )
        CircularProgressIndicator(
            color = BlueDark
        )
    }
}