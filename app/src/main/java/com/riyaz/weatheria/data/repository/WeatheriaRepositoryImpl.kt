package com.riyaz.weatheria.data.repository

import com.riyaz.weatheria.data.database.WeatheriaDao
import com.riyaz.weatheria.data.remote.WeatherApiService
import com.riyaz.weatheria.data.remote.model.asDomain
import com.riyaz.weatheria.domain.model.Forecast
import com.riyaz.weatheria.domain.model.LocationCoordinate
import com.riyaz.weatheria.domain.repository.WeatheriaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatheriaRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val api: WeatherApiService,
    private val doa: WeatheriaDao
) : WeatheriaRepository {
    override suspend fun getForecast(
        locationCoordinate: LocationCoordinate,
        queryMap: HashMap<String, String>
    ): Forecast? {dispatcher
        return withContext(dispatcher) {
            api.getForecast(locationCoordinate.longitude, locationCoordinate.latitude, queryMap).execute().body()?.asDomain()
        }
    }
}