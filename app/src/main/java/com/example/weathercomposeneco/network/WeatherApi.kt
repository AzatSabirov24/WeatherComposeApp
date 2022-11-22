package com.example.weathercomposeneco.network

import com.example.weathercomposeneco.BuildConfig
import com.example.weathercomposeneco.data.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json?key=${BuildConfig.API_KEY}")
    fun getData(
        @Query("q") city: String,
        @Query("days") days: String
    ): Call<Weather>
}