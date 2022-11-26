package com.example.weathercomposeneco.data.mapper

import android.annotation.SuppressLint
import android.util.Log
import com.example.weathercomposeneco.data.model.WeatherDataDto
import com.example.weathercomposeneco.data.model.WeatherDto
import com.example.weathercomposeneco.domain.model.WeatherData
import com.example.weathercomposeneco.domain.model.WeatherInfo
import com.example.weathercomposeneco.domain.model.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@SuppressLint("NewApi")
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }
        .groupBy { indexedWeatherData ->
            val now = LocalDateTime.now()
            if (now > indexedWeatherData.data.time)
                indexedWeatherData.index - 1 / 24 - now.hour
            else
                indexedWeatherData.index / 24
        }
        .mapValues { weatherMap ->
            weatherMap.value.map {
                it.data
            }
        }
}

@SuppressLint("NewApi")
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = now.hour
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}