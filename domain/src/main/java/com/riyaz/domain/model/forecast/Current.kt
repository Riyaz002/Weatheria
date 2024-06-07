package com.riyaz.domain.model.forecast

data class Current(
    val apparentTemperature: Double,
    val interval: Int,
    val isDay: Int,
    val precipitation: Double,
    val relativeHumidity: Int,
    val temperature: Double,
    val time: String,
    val weatherDescription: String,
    val windSpeed: Double
)
