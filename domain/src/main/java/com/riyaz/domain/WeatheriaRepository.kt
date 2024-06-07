package com.riyaz.domain

import com.riyaz.domain.model.forecast.Forecast
import com.riyaz.domain.model.forecast.LocationCoordinate
import com.riyaz.domain.model.location.LocationInformation


interface WeatheriaRepository {
    suspend fun getForecast(location: LocationCoordinate, queryMap: HashMap<String, String>): Forecast?
    suspend fun getLocationInfo(location: LocationCoordinate): LocationInformation?
}