package com.example.weathercomposeneco.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercomposeneco.presentation.WeatherState
import com.example.weathercomposeneco.presentation.ui.theme.BlueLight

@Composable
fun WeatherForecastCard(
    state: WeatherState,
    modifier: Modifier = Modifier,
    dayIndex: Int,
    day: String
) {
    state.weatherInfo?.weatherDataPerDay?.get(dayIndex)
        ?.let { data ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                backgroundColor = BlueLight,
                elevation = 0.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = day,
                        fontSize = 28.sp,
                        color = Color.White,
                        modifier = modifier.padding(
                            start = 20.dp
                        )
                    )
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    LazyRow(content = {
                        items(data) { weatherData ->
                            HourlyWeatherDisplay(
                                weatherData = weatherData,
                                modifier = Modifier
                                    .height(120.dp)
                                    .padding(horizontal = 8.dp)
                            )
                        }
                    })
                }
            }
        }
}