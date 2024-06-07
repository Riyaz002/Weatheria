package com.riyaz.data.remote.model.location

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)