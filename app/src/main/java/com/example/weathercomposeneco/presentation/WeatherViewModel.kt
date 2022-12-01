package com.example.weathercomposeneco.presentation

import android.app.Application
import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercomposeneco.domain.location.LocationTracker
import com.example.weathercomposeneco.domain.repositiory.WeatherRepository
import com.example.weathercomposeneco.domain.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
    private val applicationContext: Application
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()
    private val _cityName = MutableStateFlow("")
    val cityName: StateFlow<String> = _cityName.asStateFlow()

    init {
        fetchWeather(isUpdate = false)
    }

    fun fetchWeather(isUpdate: Boolean) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    weatherInfo = null,
                    isUpdate = isUpdate
                )
            }
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
                            latitude = it.latitude.toString(),
                            longitude = it.longitude.toString()
                        )) {
                        is Resource.Success -> {
                            delay(2000)
                            _state.update { state ->
                                state.copy(
                                    weatherInfo = result.data,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }
                        is Resource.Error -> {
                            _state.update { state ->
                                state.copy(
                                    weatherInfo = null,
                                    isLoading = false,
                                    error = result.errorMessage
                                )
                            }
                        }
                    }
                } ?: kotlin.run {
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                    )
                }
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