package com.riyaz.weatheria.domain.repository

import com.riyaz.weatheria.domain.model.Forecast


interface WeatherRepository {
    suspend fun getForecast(longitude: Double, latitude: Double, queryMap: HashMap<String, String>): Forecast?
}