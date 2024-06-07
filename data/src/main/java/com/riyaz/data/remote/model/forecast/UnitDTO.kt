package com.riyaz.data.remote.model.forecast

import com.riyaz.domain.model.forecast.Unit
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnitDTO(
    val temperature_2m: String?,
    val time: String,
    val interval: String?,
    val relative_humidity_2m: String?,
    val apparent_temperature: String?,
    val is_day: String?,
    val precipitation: String?,
    val weather_code: String?,
    val wind_speed: String?
)

fun UnitDTO.asDomain(): Unit =
    Unit(
        temperature_2m,
        time,
        interval,
        relative_humidity_2m,
        apparent_temperature,
        precipitation,
        wind_speed
    )