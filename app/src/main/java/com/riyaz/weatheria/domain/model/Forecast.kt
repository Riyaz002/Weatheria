package com.riyaz.weatheria.domain.model


data class Forecast(
    val current: Current?,
    val hourly: Hourly?,
)