package com.riyaz.data.repository

import com.riyaz.data.database.WeatheriaDao
import com.riyaz.data.remote.WeatherApiService
import com.riyaz.domain.WeatheriaRepository
import com.riyaz.domain.model.Forecast
import com.riyaz.domain.model.LocationCoordinate
import com.riyaz.data.remote.model.asDomain
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