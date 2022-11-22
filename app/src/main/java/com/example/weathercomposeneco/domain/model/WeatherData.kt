package com.example.weathercomposeneco.domain.model

data class WeatherData(
    val currentWeather: CurrentWeather
)

//data class ForecastDays(
//    days: List<>
//)
//
//data class ForecastWeather(
//    val days: List<>
//)

data class CurrentWeather(
//    val condition: Condition,
    val temperature: Double
)

data class Condition(
    val icon: String,
    val text: String
)


