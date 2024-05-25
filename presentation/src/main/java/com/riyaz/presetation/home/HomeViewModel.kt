package com.riyaz.presetation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyaz.domain.model.LocationCoordinate
import com.riyaz.domain.usecase.GetForecastUseCase
import com.riyaz.presetation.home.model.HomePageState
import com.riyaz.presetation.home.model.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(val getForecastUseCase: GetForecastUseCase) : ViewModel() {
    val state = MutableStateFlow(HomePageState())

    init {
        viewModelScope.launch {
            state.update { it.copy(forecast = getForecastUseCase(
                LocationCoordinate(77.102493, 28.704060),
                hashMapOf<String, String>().also { map ->
                    map["hourly"] = "temperature_2m"
                    "current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,weather_code,wind_speed_10m"
                        .split("&")
                        .forEach {
                            map[it.split("=").first()] = it.split("=").last()
                        }
                })
            ) }
        }
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.OnSearch -> {
                Log.e("TEST", event.searchValue)
            }
        }
    }
}