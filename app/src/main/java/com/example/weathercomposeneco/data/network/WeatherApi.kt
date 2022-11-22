package com.example.weathercomposeneco.data.network

import com.example.weathercomposeneco.BuildConfig
import com.example.weathercomposeneco.data.model.Weather
import com.example.weathercomposeneco.domain.util.Resource
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json?key=${BuildConfig.API_KEY}")
    suspend fun fetchWeather(
        @Query("q") city: String,
        @Query("days") days: String
    ): Weather
}