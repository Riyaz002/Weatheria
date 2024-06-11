package com.riyaz.data.remote

import com.riyaz.data.BuildConfig
import com.riyaz.data.remote.model.location.LocationInfoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IpGeolocationApi {

    @GET("ipgeo")
    fun getLocationInfo(
        @QueryMap query: HashMap<String, String> = hashMapOf("apiKey" to BuildConfig.IP_GEOLOCATION_API_KEY)
    ): Call<LocationInfoDTO>

    companion object{
        const val BASE_URL = "https://api.ipgeolocation.io/"
    }
}