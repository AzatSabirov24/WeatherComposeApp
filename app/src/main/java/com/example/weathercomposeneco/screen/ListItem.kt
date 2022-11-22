package com.example.weathercomposeneco.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weathercomposeneco.data.WeatherDto
import com.example.weathercomposeneco.ui.theme.BlueDark
import com.example.weathercomposeneco.ui.theme.BlueLight
import com.example.weathercomposeneco.ui.theme.WhiteMain

@Preview(showBackground = true)
@Composable
fun ListItem(item: WeatherDto) = with(item){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 4.dp
            ),
        backgroundColor = BlueLight,
        elevation = 0.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = time,
                    color = BlueDark
                )
                Text(
                    text = condition,
                    color = WhiteMain
                )
            }
            Text(
                text = currentTemp.ifEmpty { "$maxTemp/$minTemp" },
                color = BlueDark,
                style = TextStyle(
                    fontSize = 24.sp
                )
            )
            AsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .padding(
                        end = 8.dp
                    ),
                model = "https:$icon",
                contentDescription = "image weather condition"
            )
        }
    }
}