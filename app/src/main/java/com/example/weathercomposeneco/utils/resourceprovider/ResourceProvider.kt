package com.example.weathercomposeneco.utils.resourceprovider

import androidx.annotation.StringRes

interface ResourceProvider {

    fun string(@StringRes id:Int): String
}