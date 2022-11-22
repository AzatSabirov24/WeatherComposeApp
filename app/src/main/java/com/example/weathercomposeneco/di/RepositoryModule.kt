package com.example.weathercomposeneco.di

import com.example.weathercomposeneco.data.repository.WeatherRepositoryImpl
import com.example.weathercomposeneco.domain.repositiory.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository
}