package com.riyaz.data.remote.model.forecast

import com.riyaz.domain.model.forecast.Forecast
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ForecastDTO(
    val current: CurrentDTO?,
    val elevation: Double?,
    val generationtime_ms: Double?,
    val hourly: HourlyDTO?,
    val hourly_units: UnitDTO?,
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val timezone_abbreviation: String?,
    val utc_offset_seconds: Int?,
    val current_units: UnitDTO?
)

fun ForecastDTO.asDomain(): Forecast =
    Forecast(
        current = current?.asDomain(),
        hourly =  hourly?.asDomain(),
        currentUnit = current_units?.asDomain()
    )
