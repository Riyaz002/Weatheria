package com.riyaz.presetation.home.model

import com.riyaz.domain.model.forecast.Forecast
import com.riyaz.domain.model.location.LocationInformation

sealed interface HomePageUIState{
    data class Content(val forecast: Forecast? = null, val location: LocationInformation? = null): HomePageUIState
    data object Loading: HomePageUIState
}