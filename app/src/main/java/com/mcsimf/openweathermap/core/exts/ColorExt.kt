package com.mcsimf.openweathermap.core.exts

import android.graphics.Color

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */


data class ColorSet(val city: Int, val temp: Int, val date: Int)


fun Int.celsiusToColor(): ColorSet {
    return when {
        this <= 10 -> ColorSet(Color.YELLOW, Color.GREEN, Color.RED)
        this in 11..19 -> ColorSet(Color.RED, Color.YELLOW, Color.GREEN)
        this >= 20 -> ColorSet(Color.GREEN, Color.RED, Color.YELLOW)
        else -> ColorSet(0, 0, 0)
    }
}