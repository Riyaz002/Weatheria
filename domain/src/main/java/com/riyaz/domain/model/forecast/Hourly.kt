package com.riyaz.domain.model.forecast

data class Hourly(
    val temperature: List<Double>?,
    val time: List<String>?
)