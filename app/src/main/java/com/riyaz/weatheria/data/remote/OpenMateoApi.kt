package com.riyaz.weatheria.data.remote

import com.riyaz.weatheria.data.remote.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

fun interface OpenMateoApi {
    @GET("v1/forecast")
    fun getForecast(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @QueryMap query: HashMap<String, String>
    ): Call<Forecast>
}