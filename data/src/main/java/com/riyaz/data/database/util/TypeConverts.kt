package com.riyaz.data.database.util

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.riyaz.domain.model.*

@TypeConverters
object TypeConverts {
    @TypeConverter
    fun fromCurrentForecast(current: Current): String{
        val gson = Gson()
        val currentWeather = gson.toJson(current)
        return currentWeather
    }

    @TypeConverter
    fun toCurrentForecast(current: String): Current{
        val gson = Gson()
        val currentWeather = gson.getAdapter(Current::class.java).fromJson(current)
        return currentWeather
    }

    @TypeConverter
    fun fromHourlyForecast(hourly: Hourly): String{
        val gson = Gson()
        val currentWeather = gson.toJson(hourly)
        return currentWeather
    }

    @TypeConverter
    fun toHourlyForecast(hourly: String): Hourly{
        val gson = Gson()
        val hourly = gson.getAdapter(Hourly::class.java).fromJson(hourly)
        return hourly
    }
}