package com.riyaz.data.remote

import com.riyaz.data.remote.model.forecast.ForecastDTO
import com.riyaz.data.remote.model.location.LocationInfoDTO
import com.riyaz.domain.model.forecast.LocationCoordinate
import retrofit2.Call

class WeatherApiService(
    private val openMateoApi: OpenMateoApi,
    private val googleApi: IpGeolocationApi
) {
    fun getForecast(
        locationCoordinate: LocationCoordinate,
        query: HashMap<String, String>
    ): Call<ForecastDTO> {
        return openMateoApi.getForecast(locationCoordinate.longitude, locationCoordinate.latitude, query)
    }

    fun getLocationInfo(): Call<LocationInfoDTO> {
        return googleApi.getLocationInfo()
    }
}