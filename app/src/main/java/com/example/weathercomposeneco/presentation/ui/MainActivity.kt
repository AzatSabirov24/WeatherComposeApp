package com.example.weathercomposeneco.presentation.ui

import android.Manifest
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
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
            viewModel.fetchWeather()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
        setContent {
            WeatherComposeNecoTheme {
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
                            LoadingWeather(text = "Мама, привет!")
                        }
                        viewModel.state.weatherInfo != null -> {
                            Data()
                        }
                        viewModel.state.isUpdate -> {
                            LoadingWeather(text = "Мама, обновляю погоду!")
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun LoadingWeather(text: String) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(80.dp),
                text = text,
                fontSize = 48.sp,
                color = BlueDark,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(
                    id = R.drawable.me
                ),
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
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
    fun Data() {
        Column {
            MainCard(
                state = viewModel.state,
                viewModel = viewModel
            )
            Column(
                modifier = Modifier.verticalScroll(
                    state = ScrollState(0)
                )
            ) {
                WeatherForecastCard(
                    state = viewModel.state,
                    dayIndex = 0,
                    day = "Сегодня"
                )
                WeatherForecastCard(
                    state = viewModel.state,
                    dayIndex = 1,
                    day = "Завтра"
                )
                WeatherForecastCard(
                    state = viewModel.state,
                    dayIndex = 2,
                    day = "Послезавтра"
                )
            }
        }
    }
}