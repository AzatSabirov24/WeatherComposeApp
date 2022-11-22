package com.example.weathercomposeneco.di

import androidx.lifecycle.ViewModel
import com.example.weathercomposeneco.presentation.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindWeatherViewModel(viewModel: WeatherViewModel): ViewModel
}