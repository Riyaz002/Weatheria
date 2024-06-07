package com.riyaz.domain.model.forecast


data class Forecast(
    val current: Current?,
    val hourly: Hourly?,
    val currentUnit: Unit?
)