package com.riyaz.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.riyaz.domain.model.Current
import com.riyaz.domain.model.Hourly

@Entity(tableName = "forecast_table")
data class Forecast(
    @PrimaryKey val id: Int = 0,
    val current: Current,
    val hourly: Hourly
)
