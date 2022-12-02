package com.example.weathercomposeneco.presentation.ui

import android.Manifest
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercomposeneco.R
import com.example.weathercomposeneco.presentation.App
import com.example.weathercomposeneco.presentation.WeatherViewModel
import com.example.weathercomposeneco.presentation.ui.theme.BlueDark
import com.example.weathercomposeneco.presentation.ui.theme.WeatherComposeNecoTheme

class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels {
        (application as App).networkComponent.viewModelFactory()
    }
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).networkComponent.injectMainActivity(this)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.fetchWeather(
                isUpdate = false
            )
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        setContent {
            WeatherComposeNecoTheme {
                when (LocalConfiguration.current.orientation) {
                    Configuration.ORIENTATION_PORTRAIT ->
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
                            when {
                                viewModel.state.isLoading -> {
                                    LoadingWeather(
                                        text = stringResource(
                                            id = R.string.loading_weather
                                        )
                                    )
                                }
                                viewModel.state.weatherInfo != null -> {
                                    DataPortrait()
                                }
                                viewModel.state.isUpdate -> {
                                    LoadingWeather(
                                        text = stringResource(
                                            id = R.string.update_weather
                                        )
                                    )
                                }
                            }
                        }
                    else ->
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
                            when {
                                viewModel.state.isLoading -> {
                                    LoadingWeather(
                                        text = stringResource(
                                            id = R.string.loading_weather
                                        )
                                    )
                                }
                                viewModel.state.weatherInfo != null -> {
                                    DataLandscape()
                                }
                                viewModel.state.isUpdate -> {
                                    LoadingWeather(
                                        text = stringResource(
                                            id = R.string.update_weather
                                        )
                                    )
                                }
                            }
                        }
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

    @Composable
    fun DataPortrait() {
        Column {
            WeatherCard(
                isPortrait = true
            )
        }
    }

    @Composable
    fun DataLandscape() {
        Row {
            WeatherCard(
                isPortrait = false
            )
        }
    }

    @Composable
    fun WeatherCard(
        isPortrait: Boolean
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
}