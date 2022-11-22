package com.example.weathercomposeneco.data.repository

import com.example.weathercomposeneco.data.model.Weather
import com.example.weathercomposeneco.data.network.WeatherApi
import com.example.weathercomposeneco.domain.repositiory.WeatherRepository
import com.example.weathercomposeneco.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun fetchWeather(city: String, days: String): Resource<Weather> {
        return try {
            Resource.Success(
                api.fetchWeather(city, days)
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}