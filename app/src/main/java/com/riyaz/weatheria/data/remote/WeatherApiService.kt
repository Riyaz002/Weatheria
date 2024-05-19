package com.riyaz.weatheria.data.remote

import com.riyaz.weatheria.data.remote.model.ForecastDTO
import retrofit2.Call

interface WeatherApiService {
    fun getForecast(
        longitude: Double,
        latitude: Double,
        queryMap: HashMap<String, String>
    ): Call<ForecastDTO>
}