package com.riyaz.data.remote.model.location

import com.riyaz.domain.model.location.LocationInformation
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationInfoDTO(
    val calling_code: String,
    val city: String,
    val connection_type: String,
    val continent_code: String,
    val continent_name: String,
    val country_capital: String,
    val country_code2: String,
    val country_code3: String,
    val country_emoji: String,
    val country_flag: String,
    val country_name: String,
    val country_name_official: String,
    val country_tld: String,
    val currency: Currency,
    val district: String,
    val geoname_id: String,
    val ip: String,
    val is_eu: Boolean,
    val isp: String,
    val languages: String,
    val latitude: String,
    val longitude: String,
    val organization: String,
    val state_code: String,
    val state_prov: String,
    val time_zone: TimeZone,
    val zipcode: String
)


fun LocationInfoDTO.asDomain() = LocationInformation(
    continent_name,
    country_name,
    state_prov,
    city,
    zipcode,
    latitude,
    longitude,
    country_flag,
    country_emoji
)