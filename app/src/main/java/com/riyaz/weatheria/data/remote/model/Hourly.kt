package com.riyaz.weatheria.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    val temperature_2m: List<Double>?,
    val time: List<String>?
)