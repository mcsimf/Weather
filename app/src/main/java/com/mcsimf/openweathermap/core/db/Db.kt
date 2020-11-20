package com.mcsimf.openweathermap.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mcsimf.openweathermap.core.api.entity.WeatherData

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
@Database(
    entities = [WeatherData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(WeatherTypeConverter::class)
abstract class Db : RoomDatabase() {

    abstract val weatherDao: WeatherDao

}