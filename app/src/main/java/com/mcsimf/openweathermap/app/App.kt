package com.mcsimf.openweathermap.app

import android.app.Application
import com.mcsimf.openweathermap.BuildConfig
import com.mcsimf.openweathermap.core.api.apiModule
import com.mcsimf.openweathermap.core.coreModule
import com.mcsimf.openweathermap.core.weatherManagerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) androidLogger() else EmptyLogger()
            androidContext(this@App)
            modules(listOf(apiModule, coreModule, weatherManagerModule))
        }
    }


}