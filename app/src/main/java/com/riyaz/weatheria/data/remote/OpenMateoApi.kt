package com.riyaz.weatheria.data.remote

import com.riyaz.weatheria.data.remote.model.ForecastDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface OpenMateoApi: WeatherApiService {
    @GET("v1/forecast")
    override fun getForecast(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @QueryMap query: HashMap<String, String>
    ): Call<ForecastDTO>
}