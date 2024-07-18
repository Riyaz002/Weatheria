package com.riyaz.presetation.home

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riyaz.domain.model.forecast.LocationCoordinate
import com.riyaz.presetation.home.model.HomePageUIState
import kotlinx.coroutines.flow.MutableStateFlow
import com.riyaz.domain.usecase.GetForecastUseCase
import com.riyaz.domain.usecase.GetLocationInfoUseCase
import com.riyaz.domain.util.LocationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase,
    private val getLocationInfoUseCase: GetLocationInfoUseCase,
    private val locationManager: LocationManager
) : ViewModel() {
    val uiState = MutableStateFlow<HomePageUIState>(HomePageUIState.Loading)

    private fun loadForecast(locationCoordinate: LocationCoordinate) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState.update {
                HomePageUIState.Content(getForecastUseCase(locationCoordinate), getLocationInfoUseCase(locationCoordinate))
            }
        }
    }

    private fun getLocationInfo(locationCoordinate: LocationCoordinate) {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationInfoUseCase(locationCoordinate).also {
                Log.e("location", it.toString())
            }
        }
    }

    fun loadPage(){
        listenToLocation(
            onLocationUpdate = {
                loadForecast(LocationCoordinate(it.longitude, it.latitude))
                getLocationInfo(LocationCoordinate(it.longitude, it.latitude))
            }
        )
    }

    private fun listenToLocation(
        onLocationUpdate: (Location) -> Unit
    ) {
        viewModelScope.launch {
            locationManager.listenToLocation().collect{
                onLocationUpdate.invoke(it)
            }
        }
    }
}