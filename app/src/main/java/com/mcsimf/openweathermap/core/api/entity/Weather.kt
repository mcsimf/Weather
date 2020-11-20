package com.mcsimf.openweathermap.core.api.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mcsimf.openweathermap.core.db.WeatherTypeConverter

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
@Entity(tableName = "weather")
data class WeatherData(
    @PrimaryKey(autoGenerate = true) val recId: Int,
    @Embedded val coord: Coord?,
    @TypeConverters(WeatherTypeConverter::class) val weather: List<Weather>,
    val base: String,
    @Embedded val main: Main,
    val visibility: Int,
    @Embedded val wind: Wind,
    @Embedded val clouds: Clouds,
    val dt: Long,
    @Embedded(prefix = "sys_") val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)


data class Coord(val lon: Float, val lat: Float)


data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)


data class Main(
    val temp: Float,
    val feels_like: Float,
    val temp_main: Float,
    val temp_max: Float,
    val pressure: Int,
    val humidity: Int
)


data class Wind(val speed: Float, val deg: Int, val gust: Float)


data class Clouds(val all: Int)


data class Sys(val type: Int, val id: Int, val country: String, val sunrise: Long, val sunset: Long)