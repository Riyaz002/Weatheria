package com.riyaz.data.remote.model

import com.riyaz.domain.model.Hourly
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyDTO(
    val temperature_2m: List<Double>?,
    val time: List<String>?
)

fun HourlyDTO.asDomain() =
    Hourly(
        this.temperature_2m,
        this.time
    )