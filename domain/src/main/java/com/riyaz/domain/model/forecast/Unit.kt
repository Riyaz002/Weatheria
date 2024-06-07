package com.riyaz.domain.model.forecast

data class Unit(
    val temperature: String?,
    val time: String,
    val interval: String?,
    val relativeHumidity: String?,
    val apparentTemperature: String?,
    val precipitation: String?,
    val windSpeed: String?
)