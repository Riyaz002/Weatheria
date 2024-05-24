package com.riyaz.domain

import com.riyaz.domain.model.Forecast
import com.riyaz.domain.model.LocationCoordinate


interface WeatheriaRepository {
    suspend fun getForecast(location: LocationCoordinate, queryMap: HashMap<String, String>): Forecast?
}