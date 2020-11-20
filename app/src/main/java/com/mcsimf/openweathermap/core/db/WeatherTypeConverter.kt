package com.mcsimf.openweathermap.core.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mcsimf.openweathermap.core.api.entity.Weather
import java.lang.reflect.Type
import java.util.*


/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
class WeatherTypeConverter {

    companion object {

        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToWeather(data: String?): List<Weather> {
            if (data == null) {
                return Collections.emptyList()
            }
            val listType: Type = object : TypeToken<List<Weather?>?>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun weatherToString(obj: List<Weather?>?): String {
            return gson.toJson(obj)
        }
    }

}