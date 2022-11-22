package com.example.weathercomposeneco.di

import com.example.weathercomposeneco.data.location.DefaultLocationTracker
import com.example.weathercomposeneco.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(location: DefaultLocationTracker): LocationTracker
}