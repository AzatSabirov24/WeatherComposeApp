package com.example.weathercomposeneco.di

import com.example.weathercomposeneco.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {

    fun injectMainActivity(activity: MainActivity)
}