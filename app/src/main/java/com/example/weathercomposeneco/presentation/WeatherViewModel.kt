package com.example.weathercomposeneco.presentation

import android.app.Application
import android.location.Geocoder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercomposeneco.domain.location.LocationTracker
import com.example.weathercomposeneco.domain.repositiory.WeatherRepository
import com.example.weathercomposeneco.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
    private val applicationContext: Application
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set
    private val _cityName = MutableStateFlow("")
    val cityName: StateFlow<String> = _cityName.asStateFlow()

    fun fetchWeather(isUpdate: Boolean) {
        viewModelScope.launch {
            state = state.copy(
                weatherInfo = null,
                isUpdate = isUpdate
            )
        }
        viewModelScope.launch {
            locationTracker.getCurrentLocation()
                ?.let {
                    _cityName.value = getCityName(
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                    when (val result =
                        repository.fetchWeather(
                            it.latitude.toString(),
                            it.longitude.toString()
                        )) {
                        is Resource.Success -> {
                            state = state.copy(
                                weatherInfo = result.data,
                                isLoading = false,
                                error = null
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                weatherInfo = null,
                                isLoading = false,
                                error = "Проблемы с интернетом"
                            )
                        }
                    }
                } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Нужно разрешение на отслеживание местоположения"
                )
            }
        }
    }

    private fun getCityName(
        latitude: Double,
        longitude: Double
    ): String {
        val geoCoder = Geocoder(applicationContext, Locale.getDefault())
        val address = geoCoder.getFromLocation(latitude, longitude, 1)
        return address?.first()?.locality ?: ""
    }
}