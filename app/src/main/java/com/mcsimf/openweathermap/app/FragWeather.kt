package com.mcsimf.openweathermap.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mcsimf.openweathermap.R
import kotlinx.android.synthetic.main.frag_weather.view.*

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
class FragWeather : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v: View = inflater.inflate(R.layout.frag_weather, null)
        v.btn_back.setOnClickListener { findNavController().popBackStack() }

        viewModel.weatherData.observe(viewLifecycleOwner) {
            if (it?.data != null) v.item_weather.setData(it.data)
        }

        return v
    }

}