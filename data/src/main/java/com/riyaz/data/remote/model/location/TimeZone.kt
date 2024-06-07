package com.riyaz.data.remote.model.location

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimeZone(
    val current_time: String,
    val current_time_unix: Double,
    val dst_end: String,
    val dst_exists: Boolean,
    val dst_savings: Int,
    val dst_start: String,
    val is_dst: Boolean,
    val name: String,
    val offset: Double,
    val offset_with_dst: Double
)