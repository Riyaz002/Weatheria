package com.riyaz.domain.model.location

data class LocationInformation(
    val continent: String,
    val country: String,
    val state: String,
    val city: String,
    val zipcode: String,
    val latitude: String,
    val longitude: String,
    val countryFlag: String,
    val countryEmoji: String
)