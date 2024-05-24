package com.riyaz.domain.model


data class Forecast(
    val current: Current?,
    val hourly: Hourly?,
)