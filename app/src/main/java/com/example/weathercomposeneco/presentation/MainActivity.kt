//package com.example.weathercomposeneco.presentation
//
//import android.Manifest
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.activity.viewModels
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.material.CircularProgressIndicator
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.example.weathercomposeneco.R
//import com.example.weathercomposeneco.data.network.WeatherApi
//import com.example.weathercomposeneco.domain.model1.WeatherInfo
//import com.example.weathercomposeneco.presentation.screen.MainCard
//import com.example.weathercomposeneco.presentation.ui.WeatherCard
//import com.example.weathercomposeneco.presentation.ui.theme.BlueLight
//import com.example.weathercomposeneco.presentation.ui.theme.WeatherComposeNecoTheme
//import javax.inject.Inject
//
//class MainActivity : ComponentActivity() {
//
//    @Inject
//    lateinit var api: WeatherApi
//    private val viewModel: WeatherViewModel by viewModels { (application as App).networkComponent.viewModelFactory() }
//    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        (application as App).networkComponent.injectMainActivity(this)
//        permissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) {
//            viewModel.fetchWeather()
//        }
//        permissionLauncher.launch(
//            arrayOf(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//            )
//        )
//        setContent {
//            WeatherComposeNecoTheme {
//                Image(
//                    painter = painterResource(
//                        id = R.drawable.weather_bg
//                    ),
//                    contentDescription = "app background",
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .alpha(0.85f),
//                    contentScale = ContentScale.FillBounds
//                )
//
//                    if (viewModel.state.weatherInfo != null) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .background(BlueLight)
//                        ) {
////                            WeatherCard(
////                                state = viewModel.state,
////                                backgroundColor = BlueLight
////                            )
////                            Spacer(modifier = Modifier.height(16.dp))
//
//                            MainCard(state = viewModel.state)
////                        WeatherForecast(state = viewModel.state)
//                        }
//                    }
//                    if(viewModel.state.isLoading) {
//                        CircularProgressIndicator(
////                            modifier = Modifier.align(Alignment.Center)
//                        )
//                    }
//                    viewModel.state.error?.let { error ->
////                        Text(
////                            text = error,
////                            color = Color.Red,
////                            textAlign = TextAlign.Center,
////                            modifier = Modifier.align(Alignment.Center)
////                        )
//                }
//            }
//        }
//    }
//}
package com.example.weathercomposeneco.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercomposeneco.R
import com.example.weathercomposeneco.data.network.WeatherApi
import com.example.weathercomposeneco.presentation.screen.MainCard
import com.example.weathercomposeneco.presentation.ui.theme.BlueDark
import com.example.weathercomposeneco.presentation.ui.theme.WeatherComposeNecoTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var api: WeatherApi
    private val viewModel: WeatherViewModel by viewModels { (application as App).networkComponent.viewModelFactory() }
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
                            Text(
                                modifier = Modifier
                                    .padding(80.dp)
                                    .align(
                                        alignment = Alignment.TopCenter
                                    ),
                                text = "Мама, привет!",
                                fontSize = 48.sp,
                                color = BlueDark,
                                textAlign = TextAlign.Center
                            )
                            CircularProgressIndicator(
                                modifier = Modifier.align(
                                    alignment = Alignment.Center
                                ),
                                color = BlueDark
                            )
                        }
                        viewModel.state.weatherInfo != null -> {
                            Column {
                                MainCard(
                                    state = viewModel.state
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}