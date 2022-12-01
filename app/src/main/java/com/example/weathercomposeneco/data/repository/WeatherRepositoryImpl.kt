package com.example.weathercomposeneco.data.repository

import com.example.weathercomposeneco.data.mapper.toWeatherInfo
import com.example.weathercomposeneco.data.network.WeatherApi
import com.example.weathercomposeneco.domain.model.WeatherInfo
import com.example.weathercomposeneco.domain.repositiory.WeatherRepository
import com.example.weathercomposeneco.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun fetchWeather(
        latitude: String,
        longitude: String
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                api.fetchWeather(
                    latitude = latitude,
                    longitude = longitude
                )
                    .toWeatherInfo()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}