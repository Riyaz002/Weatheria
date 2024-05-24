package com.riyaz.data.database

import androidx.room.Dao
import androidx.room.Query
import com.riyaz.data.database.entity.Forecast

@Dao
interface WeatheriaDao {

    @Query("SELECT * FROM forecast_table")
    fun getForcast(): Forecast
}