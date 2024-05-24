package com.riyaz.data.remote

import com.riyaz.data.remote.model.ForecastDTO
import retrofit2.Call

interface WeatherApiService {
    fun getForecast(
        longitude: Double,
        latitude: Double,
        query: HashMap<String, String>
    ): Call<ForecastDTO>
}