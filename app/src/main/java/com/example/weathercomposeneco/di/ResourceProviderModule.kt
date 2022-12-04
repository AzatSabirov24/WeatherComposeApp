package com.example.weathercomposeneco.di

import com.example.weathercomposeneco.utils.resourceprovider.ResourceProvider
import com.example.weathercomposeneco.utils.resourceprovider.ResourceProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ResourceProviderModule {

    @Binds
    @Singleton
    abstract fun bindResourceProvider(resourceProvider: ResourceProviderImpl): ResourceProvider
}