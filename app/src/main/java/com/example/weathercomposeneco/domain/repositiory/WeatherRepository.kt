package com.example.weathercomposeneco.domain.repositiory

import com.example.weathercomposeneco.data.model.Weather
import com.example.weathercomposeneco.domain.model.CurrentWeather
import com.example.weathercomposeneco.domain.model.WeatherData
import com.example.weathercomposeneco.domain.util.Resource

interface WeatherRepository {

    suspend fun fetchWeather(
        city: String,
        days: String
    ): Resource<WeatherData>
}