package com.riyaz.weatheria.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Forecast(
    val elevation: Double?,
    @Json(name = "generationtime_ms") val generationTimeMillis: Double?,
    val hourly: Hourly?,
    @Json(name = "hourly_units") val hourlyUnits: HourlyUnit?,
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    @Json(name = "timezone_abbreviation") val timezoneAbbreviation: String?,
    @Json(name = "utc_offset_seconds") val utcOffsetSeconds: Int?
)