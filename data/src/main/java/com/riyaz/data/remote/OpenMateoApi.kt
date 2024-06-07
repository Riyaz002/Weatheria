package com.riyaz.data.remote

import com.riyaz.data.remote.model.forecast.ForecastDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface OpenMateoApi{
    @GET("v1/forecast")
    fun getForecast(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @QueryMap query: HashMap<String, String>
    ): Call<ForecastDTO>

    companion object{
        const val BASE_URL = "https://api.open-meteo.com/"
    }
}