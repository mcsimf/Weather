package com.mcsimf.openweathermap.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mcsimf.openweathermap.R
import com.mcsimf.openweathermap.core.api.entity.WeatherData
import kotlinx.android.synthetic.main.frag_cache.view.*

/**
 * @author Maksym Fedyay on 11/13/20 (mcsimf@gmail.com).
 */
class FragCache : Fragment() {


    private val viewModel: WeatherViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.frag_cache, null);

        v.btn_back.setOnClickListener {
            findNavController().popBackStack()
        }

        val adapter = WeatherAdapter()

        viewModel.getCachedWeatherCasts().observe(viewLifecycleOwner) {
            adapter.list = it
        }

        val llm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        v.recycler_view.layoutManager = llm
        v.recycler_view.adapter = adapter
        return v
    }


    inner class WeatherAdapter : RecyclerView.Adapter<ViewHolder>() {

        var list: List<WeatherData>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemWeather.create(parent.context))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            list?.get(position)?.let { holder.item.setData(it) }
        }

        override fun getItemCount(): Int = if (null == list) 0 else list!!.size
    }


    class ViewHolder(val item: ItemWeather) : RecyclerView.ViewHolder(item)

}