package com.riyaz.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyUnitDTO(
    val temperature_2m: String?,
    val time: String
)