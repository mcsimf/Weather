package com.mcsimf.openweathermap.core

import androidx.room.Room
import com.mcsimf.openweathermap.core.db.Db
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.dsl.module


val coreModule = module {
    single {
        Room.databaseBuilder(androidContext(), Db::class.java, "openweathermap-db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<Db>().weatherDao }
    single { Core() }
}


/**
 *
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
class Core : KoinComponent {

    // All managers should be accessed over Core instance.

    val weatherManager: WeatherManager by inject()

}