package com.example.weathercomposeneco.data.model1

import com.google.gson.annotations.SerializedName

data class Location(
    val country: String,
    val lat: Double,
    @SerializedName("localtime")
    val localTime: String,
    val localtime_epoch: Int,
    val lon: Double,
    @SerializedName("name")
    val city: String,
    val region: String,
    val tz_id: String
)