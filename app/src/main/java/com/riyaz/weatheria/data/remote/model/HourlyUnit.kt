package com.riyaz.weatheria.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyUnits(
    val temperature_2m: String?,
    val time: String?
)