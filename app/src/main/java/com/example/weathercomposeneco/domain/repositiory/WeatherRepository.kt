package com.example.weathercomposeneco.domain.repositiory

import com.example.weathercomposeneco.domain.model.WeatherInfo
import com.example.weathercomposeneco.domain.util.Resource

interface WeatherRepository {

    suspend fun fetchWeather(
        latitude: String,
        longitude: String
    ): Resource<WeatherInfo>
}