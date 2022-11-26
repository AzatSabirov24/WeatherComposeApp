package com.example.weathercomposeneco.presentation

import com.example.weathercomposeneco.domain.model.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = true,
    val isUpdate: Boolean = false,
    val error: String? = null
)