package com.example.weathercomposeneco.presentation

import android.app.Application
import com.example.weathercomposeneco.di.DaggerNetworkComponent
import com.example.weathercomposeneco.di.NetworkComponent

class App : Application() {

    lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()
        networkComponent = DaggerNetworkComponent.factory()
            .app(this)
    }
}