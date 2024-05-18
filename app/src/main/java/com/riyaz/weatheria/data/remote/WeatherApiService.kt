package com.riyaz.weatheria.data.remote

import com.riyaz.weatheria.data.remote.model.ForecastDTO
import retrofit2.Call
import retrofit2.http.QueryMap

interface WeatherApiService {
    fun getForecast(
        longitude: Double,
        latitude: Double,
        queryMap: QueryMap
    ): Call<ForecastDTO>
}