package com.example.weathercomposeneco.presentation

import com.example.weathercomposeneco.data.model.Weather

data class WeatherState(
    val weatherInfo: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
