package com.example.weathercomposeneco.data.repository

import com.example.weathercomposeneco.data.mapper.toCurrentDomain
import com.example.weathercomposeneco.data.mapper.toWeatherDomain
import com.example.weathercomposeneco.data.model.Weather
import com.example.weathercomposeneco.data.network.WeatherApi
import com.example.weathercomposeneco.domain.model.CurrentWeather
import com.example.weathercomposeneco.domain.model.WeatherData
import com.example.weathercomposeneco.domain.repositiory.WeatherRepository
import com.example.weathercomposeneco.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun fetchWeather(city: String, days: String): Resource<WeatherData> {
        return try {
            Resource.Success(
                api.fetchWeather(city, days).toWeatherDomain()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

//    override suspend fun fetchWeather(city: String, days: String): Resource<CurrentWeather> {
//        return try {
//            Resource.Success(
//                api.fetchWeather(city, days).toCurrentDomain()
//            )
//        } catch (e: Exception) {
//            Resource.Error(e.message ?: "An unknown error occurred")
//        }
//    }
}