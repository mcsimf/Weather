package com.mcsimf.openweathermap.app

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.mcsimf.openweathermap.R
import com.mcsimf.openweathermap.core.api.entity.WeatherData
import com.mcsimf.openweathermap.core.api.iconUrl
import com.mcsimf.openweathermap.core.exts.celsiusToColor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_weather.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
class ItemWeather @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs) {


    companion object {
        fun create(context: Context): ItemWeather {
            val item = ItemWeather(context)
            val lp = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            item.layoutParams = lp
            return item
        }
    }


    init {
        LayoutInflater.from(context).inflate(R.layout.item_weather, this)
    }


    @SuppressLint("SetTextI18n")
    fun setData(data: WeatherData) {
        val weather = data.weather[0]
        Picasso.get().load(weather.icon.iconUrl()).into(ic_weather)
        weather_desc.text = weather.description.capitalize(Locale.getDefault())
        city_name.text = data.name
        temp.text = " " + data.main.temp.toInt().toString() + "\t°"
        date.text = convertLongToTime(data.dt)
        //setColors(data.main.temp.toInt())
    }


    private fun setColors(tempValue: Int) {
        val colorSet = tempValue.celsiusToColor()
        city_name.setTextColor(colorSet.city)
        temp.setTextColor(colorSet.temp)
        date.setTextColor(colorSet.date)
    }


    private fun convertLongToTime(time: Long): String {
        val date = Date(time * 1000)
        val format = SimpleDateFormat("EEE\ndd", Locale.getDefault())
        return format.format(date)
    }

}