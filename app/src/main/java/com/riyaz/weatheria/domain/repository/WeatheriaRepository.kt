package com.riyaz.weatheria.domain.repository

import com.riyaz.weatheria.domain.model.Forecast
import com.riyaz.weatheria.domain.model.LocationCoordinate


interface WeatheriaRepository {
    suspend fun getForecast(location: LocationCoordinate, queryMap: HashMap<String, String>): Forecast?
}