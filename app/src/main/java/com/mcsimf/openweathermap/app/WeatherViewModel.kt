package com.mcsimf.openweathermap.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mcsimf.openweathermap.core.Core
import com.mcsimf.openweathermap.core.Res
import com.mcsimf.openweathermap.core.Res.State.*
import com.mcsimf.openweathermap.core.api.entity.WeatherData
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
class WeatherViewModel : ViewModel(), KoinComponent {

    private val core: Core by inject()


    /*
     * Exposing weather data directly from network
     */

    /**
     * MediatorLiveData for decoupling repository LivaData from UI.
     */
    private val _weatherData = MediatorLiveData<Res<WeatherData>>()


    val weatherData: LiveData<Res<WeatherData>>
        get() {
            return _weatherData
        }


    fun getWeatherByCity(city: String) {
        if (_weatherData.value?.state == LOADING) return // Prevent double request (extra gate)
        val ld = core.weatherManager.getWeatherByCity(city)
        _weatherData.addSource(ld) {
            if (it.state == ERROR || it.state == COMPLETE) _weatherData.removeSource(ld)
            _weatherData.value = it
        }
    }


    /*
     * Exposing weather data cached in local repository
     */


    fun getCachedWeatherCasts(): LiveData<List<WeatherData>> =
        core.weatherManager.getCachedWeatherDate()


    fun hasCache() = core.weatherManager.hasCache()
}