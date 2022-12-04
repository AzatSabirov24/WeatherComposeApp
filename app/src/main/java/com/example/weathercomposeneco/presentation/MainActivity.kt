package com.example.weathercomposeneco.presentation

import android.Manifest
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalConfiguration
import com.example.weathercomposeneco.presentation.ui.DataLandscape
import com.example.weathercomposeneco.presentation.ui.DataPortrait
import com.example.weathercomposeneco.presentation.ui.MainScreen
import com.example.weathercomposeneco.presentation.ui.theme.WeatherComposeNecoTheme

class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels {
        (application as App).mainComponent.viewModelFactory()
    }
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).mainComponent.injectMainActivity(this)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (viewModel.state.weatherInfo == null)
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
                        MainScreen(
                            data = {
                                DataPortrait(
                                    viewModel = viewModel
                                )
                            },
                            viewModel = viewModel,
                            activity = this
                        )
                    else ->
                        MainScreen(
                            data = {
                                DataLandscape(
                                    viewModel = viewModel
                                )
                            },
                            viewModel = viewModel,
                            activity = this
                        )
                }
                BackHandler {
                    finish()
                }
            }
        }
    }
}