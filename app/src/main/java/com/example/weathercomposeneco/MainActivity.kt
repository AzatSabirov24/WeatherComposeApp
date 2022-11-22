package com.example.weathercomposeneco

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weathercomposeneco.data.Weather
import com.example.weathercomposeneco.network.WeatherApi
import com.example.weathercomposeneco.screen.MainCard
import com.example.weathercomposeneco.screen.TabLayout
import com.example.weathercomposeneco.ui.theme.WeatherComposeNecoTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var api: WeatherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).networkComponent.injectMainActivity(this)
        setContent {
            val response = api.getData("Shymkent", "1")
            response.enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    Log.d(
                        "getData",
                        "${
                            response.body()
                        }"
                    )
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    Log.d("getData", "onFailure: ${t.message}")
                }
            })

            WeatherComposeNecoTheme {
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
                Column {
                    MainCard()
                    TabLayout()
                }
            }
        }
    }
}