package com.example.weathercomposeneco.presentation

import com.example.weathercomposeneco.data.model.Weather
import com.example.weathercomposeneco.domain.model.CurrentWeather
import com.example.weathercomposeneco.domain.model.WeatherData

data class WeatherState(
    val weatherInfo: WeatherData? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

//data class WeatherState(
//    val weatherInfo: CurrentWeather? = null,
//    val isLoading: Boolean = false,
//    val error: String? = null
//)
