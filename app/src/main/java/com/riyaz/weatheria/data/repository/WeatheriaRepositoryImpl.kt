package com.riyaz.weatheria.data.repository

import com.riyaz.weatheria.data.database.WeatheriaDao
import com.riyaz.weatheria.data.remote.OpenMateoApi
import com.riyaz.weatheria.data.remote.model.asDomain
import com.riyaz.weatheria.domain.model.Current
import com.riyaz.weatheria.domain.model.Forecast
import com.riyaz.weatheria.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.http.QueryMap
import javax.inject.Inject

class WeatheriaRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val api: OpenMateoApi,
    private val doa: WeatheriaDao
) : WeatherRepository {
    override suspend fun getForecast(
        longitude: Double,
        latitude: Double,
        queryMap: HashMap<String, String>
    ): Forecast? {
        return withContext(dispatcher) {
            api.getForecast(longitude, latitude, queryMap).execute().body()?.asDomain()
        }
    }
}