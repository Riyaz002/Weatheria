package com.riyaz.weatheria.data.remote.model

data class Hourly(
    val temperature_2m: List<Double>,
    val time: List<String>
)