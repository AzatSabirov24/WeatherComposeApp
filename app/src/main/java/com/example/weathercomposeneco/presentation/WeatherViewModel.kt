package com.example.weathercomposeneco.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercomposeneco.domain.location.LocationTracker
import com.example.weathercomposeneco.domain.repositiory.WeatherRepository
import com.example.weathercomposeneco.domain.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun fetchWeather() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            val result = repository.fetchWeather("Kazan", "5")
            Log.d("fetchWeather", "fetchWeather() called: ${result.data}")
            state = when (result) {
                is Resource.Success -> state.copy(
                    isLoading = false,
                    weatherInfo = result.data,
                    error = null
                )
                is Resource.Error -> state.copy(
                    isLoading = false,
                    weatherInfo = null,
                    error = result.errorMessage
                )
            }
        }
    }
}