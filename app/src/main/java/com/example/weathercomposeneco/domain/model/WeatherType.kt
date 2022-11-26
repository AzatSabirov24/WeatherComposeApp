package com.example.weathercomposeneco.domain.model

import androidx.annotation.DrawableRes
import com.example.weathercomposeneco.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {

    object ClearSky : WeatherType(
        weatherDesc = "Ясно",
        iconRes = R.drawable.ic_sunny
    )

    object MainlyClear : WeatherType(
        weatherDesc = "Облачно",
        iconRes = R.drawable.ic_cloudy
    )

    object PartlyCloudy : WeatherType(
        weatherDesc = "Переменная облачность",
        iconRes = R.drawable.ic_cloudy
    )

    object Overcast : WeatherType(
        weatherDesc = "Пасмурная погода",
        iconRes = R.drawable.ic_cloudy
    )

    object Foggy : WeatherType(
        weatherDesc = "Туман",
        iconRes = R.drawable.ic_very_cloudy
    )

    object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing rime fog",
        iconRes = R.drawable.ic_very_cloudy
    )

    object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        iconRes = R.drawable.ic_rainshower
    )

    object ModerateDrizzle : WeatherType(
        weatherDesc = "Мелкая изморось",
        iconRes = R.drawable.ic_rainshower
    )

    object DenseDrizzle : WeatherType(
        weatherDesc = "Изморось",
        iconRes = R.drawable.ic_rainshower
    )

    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy
    )

    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Слабая ледяная морось",
        iconRes = R.drawable.ic_snowyrainy
    )

    object SlightRain : WeatherType(
        weatherDesc = "Мелкий дождь",
        iconRes = R.drawable.ic_rainy
    )

    object ModerateRain : WeatherType(
        weatherDesc = "Дождь",
        iconRes = R.drawable.ic_rainy
    )

    object HeavyRain : WeatherType(
        weatherDesc = "Сильный дождь",
        iconRes = R.drawable.ic_rainy
    )

    object HeavyFreezingRain : WeatherType(
        weatherDesc = "Сильный ледяной дождь",
        iconRes = R.drawable.ic_snowyrainy
    )

    object SlightSnowFall : WeatherType(
        weatherDesc = "Слабый снег",
        iconRes = R.drawable.ic_snowy
    )

    object ModerateSnowFall : WeatherType(
        weatherDesc = "Снег",
        iconRes = R.drawable.ic_heavysnow
    )

    object HeavySnowFall : WeatherType(
        weatherDesc = "Снегопад",
        iconRes = R.drawable.ic_heavysnow
    )

    object SnowGrains : WeatherType(
        weatherDesc = "Крупный снег",
        iconRes = R.drawable.ic_heavysnow
    )

    object SlightRainShowers : WeatherType(
        weatherDesc = "Мелкий дождь",
        iconRes = R.drawable.ic_rainshower
    )

    object ModerateRainShowers : WeatherType(
        weatherDesc = "Дождь",
        iconRes = R.drawable.ic_rainshower
    )

    object ViolentRainShowers : WeatherType(
        weatherDesc = "Ливень",
        iconRes = R.drawable.ic_rainshower
    )

    object SlightSnowShowers : WeatherType(
        weatherDesc = "Слабые дожди со снегом",
        iconRes = R.drawable.ic_snowy
    )

    object HeavySnowShowers : WeatherType(
        weatherDesc = "Сильный снегопад",
        iconRes = R.drawable.ic_snowy
    )

    object ModerateThunderstorm : WeatherType(
        weatherDesc = "Гроза",
        iconRes = R.drawable.ic_thunder
    )

    object SlightHailThunderstorm : WeatherType(
        weatherDesc = "Гроза с небольшим градом",
        iconRes = R.drawable.ic_rainythunder
    )

    object HeavyHailThunderstorm : WeatherType(
        weatherDesc = "Гроза с сильным градом",
        iconRes = R.drawable.ic_rainythunder
    )

    companion object {

        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}