package com.example.weathercomposeneco

import android.app.Application
import com.example.weathercomposeneco.di.DaggerNetworkComponent
import com.example.weathercomposeneco.di.NetworkComponent

class App : Application() {

    lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()
        networkComponent = DaggerNetworkComponent.create()
    }
}