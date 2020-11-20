package com.mcsimf.openweathermap.core.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mcsimf.openweathermap.core.api.entity.WeatherData

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
@Dao
abstract class WeatherDao {

    /**
     * Insert weather data.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(weather: WeatherData)

    /**
     * Trim table. Delete all and leave only latest 5 records.
     */
    @Query("DELETE FROM weather WHERE recId NOT IN ( SELECT recId FROM weather ORDER BY recId DESC LIMIT 5)")
    abstract suspend fun trim()

    /**
     * Cache weather data. Use this function to keep records trimmed.
     */
    @Transaction
    open suspend fun cache(weather: WeatherData) {
        insert(weather)
        trim()
    }

    /**
     * Select cached records.
     */
    @Query("SELECT * FROM weather ORDER BY recId DESC")
    abstract fun cache(): LiveData<List<WeatherData>>


    @Query("SELECT COUNT(*) FROM weather")
    abstract fun hasCache(): LiveData<Int>

}