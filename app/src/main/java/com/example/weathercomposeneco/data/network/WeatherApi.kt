package com.example.weathercomposeneco.data.network

import com.example.weathercomposeneco.data.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast?latitude=55.796391&longitude=49.108891&hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun fetchWeather(): WeatherDto
}