package com.riyaz.weatheria.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    val temperature: List<Double>?,
    val time: List<String>?
)