package com.mcsimf.openweathermap.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mcsimf.openweathermap.core.api.WeatherAPI
import com.mcsimf.openweathermap.core.api.entity.WeatherData
import com.mcsimf.openweathermap.core.db.Db
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.dsl.module
import retrofit2.HttpException
import java.io.IOException

val weatherManagerModule = module {
    factory { WeatherManager() }
}

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
class WeatherManager : KoinComponent {


    private val weatherApi: WeatherAPI by inject()


    private val db: Db by inject()


    /**
     * Fetch weather data from network for a city
     * specified by name.
     */
    fun getWeatherByCity(city: String): LiveData<Res<WeatherData>> = liveData {
        emit(Res.loading())
        try {
            val data = weatherApi.getWeatherByCity(city)
            emit(Res.complete(data))
            db.weatherDao.cache(data) // Cache the search result
        } catch (e: IOException) {
            emit(Res.error<WeatherData>(e.message))
        } catch (e: HttpException) {
            emit(Res.error<WeatherData>(e.message, e.code()))
        } catch (e: Exception) {
            emit(Res.error<WeatherData>(e.message))
        }
    }


    /**
     * Returns cached weather
     * casts for previous last 5 searches
     */
    fun getCachedWeatherDate(): LiveData<List<WeatherData>> = db.weatherDao.cache()


    /**
     * Checks if cache available.
     */
    fun hasCache() = db.weatherDao.hasCache()

}