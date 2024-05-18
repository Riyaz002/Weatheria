package com.riyaz.weatheria.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyUnitDTO(
    val temperature_2m: String?,
    val time: String
)