package com.example.weathercomposeneco.data.mapper

import com.example.weathercomposeneco.data.model.Current
import com.example.weathercomposeneco.data.model.Weather
import com.example.weathercomposeneco.domain.model.CurrentWeather
import com.example.weathercomposeneco.domain.model.WeatherData

fun Current.toCurrentDomain() =
    CurrentWeather(
        temperature = temp_c
    )

fun Weather.toWeatherDomain(): WeatherData {
    return WeatherData(
        currentWeather = current.toCurrentDomain()
    )
}