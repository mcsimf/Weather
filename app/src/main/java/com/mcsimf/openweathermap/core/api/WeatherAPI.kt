package com.mcsimf.openweathermap.core.api

import com.mcsimf.openweathermap.core.api.entity.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
interface WeatherAPI {

    /**
     *
     */
    @GET("/data/2.5/weather?appid=7587eaff3affbf8e56a81da4d6c51d06&units=metric")
    suspend fun getWeatherByCity(@Query("q") city: String): WeatherData

}