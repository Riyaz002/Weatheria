package com.riyaz.domain.usecase

import com.riyaz.domain.model.forecast.Forecast
import com.riyaz.domain.model.forecast.LocationCoordinate
import com.riyaz.domain.WeatheriaRepository

class GetForecastUseCase(
    private val repository: WeatheriaRepository
) {
    private val query = hashMapOf<String, String>().also { map ->
        map["hourly"] = "temperature_2m"
        "current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,weather_code,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,weather_code,wind_speed_10m"
            .split("&")
            .forEach {
                map[it.split("=").first()] = it.split("=").last()
            }
    }
    suspend operator fun invoke(locationCoordinate: LocationCoordinate): Forecast?{
        return repository.getForecast(locationCoordinate, query)
    }
}