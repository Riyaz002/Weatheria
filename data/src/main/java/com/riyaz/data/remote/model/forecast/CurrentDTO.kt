package com.riyaz.data.remote.model.forecast

import com.riyaz.data.remote.model.forecast.WeatherConditions.Companion.condition
import com.riyaz.domain.model.forecast.Current
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentDTO(
    val apparent_temperature: Double,
    val interval: Int,
    val is_day: Int,
    val precipitation: Double,
    val relative_humidity_2m: Int,
    val temperature_2m: Double,
    val time: String,
    val weather_code: Int,
    val wind_speed_10m: Double
)

fun CurrentDTO.asDomain(): Current =
    Current(
        this.apparent_temperature,
        this.interval,
        this.is_day,
        this.precipitation,
        this.relative_humidity_2m,
        this.temperature_2m,
        this.time,
        this.weather_code.condition().description,
        this.wind_speed_10m
    )