package com.example.weathercomposeneco.presentation

import com.example.weathercomposeneco.domain.model.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)