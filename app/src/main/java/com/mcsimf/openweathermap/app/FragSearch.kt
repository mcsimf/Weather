package com.mcsimf.openweathermap.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mcsimf.openweathermap.R
import com.mcsimf.openweathermap.core.Res
import com.mcsimf.openweathermap.core.Res.State.*
import kotlinx.android.synthetic.main.frag_search.*
import kotlinx.android.synthetic.main.frag_search.view.*

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
class FragSearch : Fragment() {


    private val viewModel: WeatherViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v: View = inflater.inflate(R.layout.frag_search, null)

        // Make history btn hidden if no history available
        viewModel.hasCache().observe(viewLifecycleOwner) {
            v.btn_history.visible(it > 0)
        }

        v.btn_history.setOnClickListener {
            v.search_text.hideSoftKeyboard()
            findNavController().navigate(R.id.action_fragSearch_to_fragCache)
        }

        // Search button
        v.btn_search.setOnClickListener {
            v.search_text.hideSoftKeyboard()
            search(v.search_text.text.toString())
        }

        // Soft keyboard action
        v.search_text.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                v.search_text.hideSoftKeyboard()
                search(v.search_text.text.toString())
                true
            } else {
                false
            }
        }

        return v
    }


    /**
     *
     */
    private fun search(city: String) {

        if (city.trim().isBlank()) return // Ignore if empty

        viewModel.getWeatherByCity(city)

        viewModel.weatherData.observe(viewLifecycleOwner) {
            when (it.state) {
                LOADING -> progress(true)
                COMPLETE -> {
                    viewModel.weatherData.removeObservers(viewLifecycleOwner)
                    progress(false)
                    findNavController().navigate(
                        R.id.action_fragSearch_to_fragWeather,
                        null,
                        null/*options*/
                    )
                }
                ERROR -> {
                    viewModel.weatherData.removeObservers(viewLifecycleOwner)
                    progress(false)
                    // TODO: Toast the error
                    showError(it.error)
                }
            }
        }
    }


    /**
     *
     */
    private fun progress(show: Boolean) {
        progress_bar.visible(show)
        btn_search.isEnabled = !show
        search_text.isEnabled = !show
    }


    /**
     *
     */
    private fun showError(error: Res.Error?) {
        if (error?.code == 404) {
            Snackbar.make(
                search_text,
                getString(R.string.msg_error_no_city),
                Snackbar.LENGTH_INDEFINITE
            ).setAction(getString(R.string.btn_ok)) {}.show()
        }

        // TODO: Show other error cases

    }


}