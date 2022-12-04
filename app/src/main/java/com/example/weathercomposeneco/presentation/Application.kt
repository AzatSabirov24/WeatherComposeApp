package com.example.weathercomposeneco.presentation

import android.app.Application
import com.example.weathercomposeneco.di.DaggerMainComponent
import com.example.weathercomposeneco.di.MainComponent

class App : Application() {

    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.factory()
            .app(
                app = this,
                context = this
            )
    }
}