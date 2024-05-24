package com.riyaz.data.remote.model

import com.riyaz.domain.model.Forecast
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ForecastDTO(
    val current: CurrentDTO?,
    val elevation: Double?,
    val generationtime_ms: Double?,
    val hourly: HourlyDTO?,
    val hourly_units: HourlyUnitDTO?,
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val timezone_abbreviation: String?,
    val utc_offset_seconds: Int?
)

fun ForecastDTO.asDomain(): Forecast =
    Forecast(
        current = this.current?.asDomain(),
        hourly =  this.hourly?.asDomain()
    )