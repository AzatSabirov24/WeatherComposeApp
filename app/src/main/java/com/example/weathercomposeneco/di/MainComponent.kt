package com.example.weathercomposeneco.di

import android.app.Application
import android.content.Context
import com.example.weathercomposeneco.presentation.MainActivity
import com.example.weathercomposeneco.presentation.ViewModelFactory
import com.example.weathercomposeneco.utils.resourceprovider.ResourceProvider
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        FusedLocationModule::class,
        LocationModule::class,
        ResourceProviderModule::class
    ]
)
@Singleton
interface MainComponent {

    fun injectMainActivity(activity: MainActivity)

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {

        fun app(
            @BindsInstance
            app: Application,
            @BindsInstance
            context: Context
        ): MainComponent
    }
}