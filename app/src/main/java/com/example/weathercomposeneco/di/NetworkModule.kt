package com.example.weathercomposeneco.di

import com.example.weathercomposeneco.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY })
        .build()

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(WeatherApi::class.java)

    private const val BASE_URL = "https://api.open-meteo.com/"
}