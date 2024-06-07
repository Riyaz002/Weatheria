package com.riyaz.data.repository

import com.riyaz.data.database.WeatheriaDao
import com.riyaz.data.remote.WeatherApiService
import com.riyaz.domain.WeatheriaRepository
import com.riyaz.domain.model.forecast.Forecast
import com.riyaz.domain.model.forecast.LocationCoordinate
import com.riyaz.data.remote.model.forecast.asDomain
import com.riyaz.data.remote.model.location.LocationInfoDTO
import com.riyaz.data.remote.model.location.asDomain
import com.riyaz.domain.model.location.LocationInformation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatheriaRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val api: WeatherApiService,
    private val doa: WeatheriaDao
) : WeatheriaRepository {

    override suspend fun getForecast(
        location: LocationCoordinate,
        queryMap: HashMap<String, String>
    ): Forecast? {dispatcher
        return withContext(dispatcher) {
            api.getForecast(location, queryMap).execute().body()?.asDomain()
        }
    }

    override suspend fun getLocationInfo(location: LocationCoordinate): LocationInformation? {
        return withContext(dispatcher) {
            api.getLocationInfo().execute().body()?.asDomain()
        }
    }
}