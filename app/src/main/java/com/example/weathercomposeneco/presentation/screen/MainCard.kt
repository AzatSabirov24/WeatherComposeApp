package com.example.weathercomposeneco.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercomposeneco.R
import com.example.weathercomposeneco.presentation.WeatherState
import com.example.weathercomposeneco.presentation.ui.theme.BlueDark
import com.example.weathercomposeneco.presentation.ui.theme.BlueLight
import com.example.weathercomposeneco.presentation.ui.theme.WhiteMain

@Composable
fun MainCard(
    state: WeatherState,
) {
    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = BlueLight,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            state.weatherInfo?.currentWeatherData?.let { weatherData ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(
                                top = 8.dp,
                                start = 8.dp
                            ),
                            text = "Казань",
                            style = TextStyle(
                                fontSize = 40.sp
                            ),
                            color = BlueDark
                        )
                        Image(
                            modifier = Modifier.padding(
                                top = 8.dp,
                                end = 8.dp
                            ),
                            painter = painterResource(
                                id = weatherData.weatherType.iconRes
                            ),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = weatherData.temperatureCelsius.toString(),
                        style = TextStyle(
                            fontSize = 72.sp
                        ),
                        color = BlueDark
                    )
                    Text(
                        text = weatherData.weatherType.weatherDesc,
                        style = TextStyle(
                            fontSize = 32.sp
                        ),
                        color = WhiteMain
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = { /* TODO */ }) {
                            Icon(
                                painterResource(
                                    id = R.drawable.ic_search_24
                                ),
                                contentDescription = "icon search",
                                tint = WhiteMain
                            )
                        }
                        IconButton(
                            onClick = { /* TODO */ }) {
                            Icon(
                                painterResource(
                                    id = R.drawable.ic_sync_24
                                ),
                                contentDescription = "icon search",
                                tint = WhiteMain
                            )
                        }
                    }
                }
            }
        }
    }
}