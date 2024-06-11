package com.riyaz.data.remote

import com.riyaz.data.BuildConfig
import com.riyaz.data.remote.model.location.LocationInfoDTO
import retrofit2.Call
import retrofit2.http.GET

interface IpGeolocationApi {

    @GET("ipgeo?apiKey=$API_KEY")
    fun getLocationInfo(): Call<LocationInfoDTO>

    companion object{
        const val BASE_URL = "https://api.ipgeolocation.io/"
        const val API_KEY = BuildConfig.IP_GEOLOCATION_API_KEY
    }
}