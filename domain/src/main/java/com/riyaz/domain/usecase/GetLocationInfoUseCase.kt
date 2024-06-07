package com.riyaz.domain.usecase

import com.riyaz.domain.model.forecast.LocationCoordinate
import com.riyaz.domain.WeatheriaRepository
import com.riyaz.domain.model.location.LocationInformation

class GetLocationInfoUseCase(
    private val repository: WeatheriaRepository
) {
    suspend operator fun invoke(locationCoordinate: LocationCoordinate): LocationInformation?{
        return repository.getLocationInfo(locationCoordinate)
    }
}