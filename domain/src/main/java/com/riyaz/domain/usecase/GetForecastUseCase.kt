package com.riyaz.domain.usecase

import com.riyaz.domain.model.Forecast
import com.riyaz.domain.model.LocationCoordinate
import com.riyaz.domain.WeatheriaRepository

class GetForecastUseCase(
    private val repository: WeatheriaRepository
) {
    suspend operator fun invoke(locationCoordinate: LocationCoordinate, query: HashMap<String, String>): Forecast?{
        return repository.getForecast(locationCoordinate, query)
    }
}