package com.riyaz.presetation.home.model

import com.riyaz.domain.model.forecast.Forecast

sealed interface HomePageUIState{
    data class Loaded(val forecast: Forecast? = null): HomePageUIState
    data object Loading: HomePageUIState
}