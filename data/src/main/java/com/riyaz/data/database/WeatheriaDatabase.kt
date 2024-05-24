package com.riyaz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.riyaz.data.database.entity.Forecast
import com.riyaz.data.database.util.TypeConverts

@Database(entities = [Forecast::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverts::class)
abstract class WeatheriaDatabase: RoomDatabase() {
    abstract val weatheriaDao: WeatheriaDao

    companion object{
        const val DATABASE_NAME = "weatheria_database"
    }
}