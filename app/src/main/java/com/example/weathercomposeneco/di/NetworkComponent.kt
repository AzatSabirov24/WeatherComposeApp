package com.example.weathercomposeneco.di

import android.app.Application
import com.example.weathercomposeneco.presentation.ui.MainActivity
import com.example.weathercomposeneco.presentation.ViewModelFactory
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
        LocationModule::class
    ]
)
@Singleton
interface NetworkComponent {

    fun injectMainActivity(activity: MainActivity)

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {

        fun app(
            @BindsInstance
            app: Application
        ): NetworkComponent
    }
}