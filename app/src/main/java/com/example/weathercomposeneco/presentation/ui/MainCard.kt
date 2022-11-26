package com.example.weathercomposeneco.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathercomposeneco.presentation.WeatherState
import com.example.weathercomposeneco.presentation.ui.theme.BlueDark
import com.example.weathercomposeneco.presentation.ui.theme.BlueLight
import com.example.weathercomposeneco.presentation.ui.theme.WhiteMain
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun MainCard(
    state: WeatherState,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                    Column {
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
                        Text(
                            modifier = Modifier.padding(
                                start = 8.dp
                            ),
                            text = LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("HH:mm")),
                            style = TextStyle(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = Color.White
                        )
                    }

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
                    text = "${weatherData.temperatureCelsius}°C",
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
                    color = WhiteMain,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )
            }
        }
    }
}