package com.riyaz.weatheria.domain.usecase

import com.riyaz.weatheria.domain.model.Forecast
import com.riyaz.weatheria.domain.model.LocationCoordinate
import com.riyaz.weatheria.domain.repository.WeatheriaRepository

class GetForecastUseCase(
    private val repository: WeatheriaRepository
) {
    suspend operator fun invoke(locationCoordinate: LocationCoordinate, query: HashMap<String, String>): Forecast?{
        return repository.getForecast(locationCoordinate, query)
    }
}