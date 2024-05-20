package com.riyaz.weatheria.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.riyaz.weatheria.domain.model.Current
import com.riyaz.weatheria.domain.model.Hourly

@Entity(tableName = "forecast_table")
data class Forecast(
    @PrimaryKey val id: Int = 0,
    val current: Current,
    val hourly: Hourly
)
