package com.epam.weatherforecastapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.epam.weatherforecastapp.model.CityForecast
import com.epam.weatherforecastapp.model.ForecastElement
import com.epam.weatherforecastapp.model.Header
import com.epam.weatherforecastapp.model.Weather.*

class ForecastAdapter(
    private val items: List<ForecastElement>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM -> {
                val item = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_fragment, parent, false) as ConstraintLayout
                ItemViewHolder(item)
            }
            HEADER -> {
                val section = LayoutInflater.from(parent.context)
                    .inflate(R.layout.header_fragment, parent, false) as FrameLayout
                HeaderViewHolder(section)
            }
            else -> throw IllegalArgumentException("No such view type!")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        return if (items[position].isHeader) HEADER else ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val el = items[position]
        if (holder is ItemViewHolder) {
            el as CityForecast
            holder.cityName.text = el.cityName
            holder.usefuleInfo.text = el.info
            holder.temperature.text = el.temperature.toString()
            holder.units.text = el.units
            loadWeatherIcon(holder, el)

            Glide.with(holder.cityImage.context)
                .load(el.image)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.cityImage)
        } else {
            holder as HeaderViewHolder
            el as Header
            holder.title.text = el.title
        }
    }

    private fun loadWeatherIcon(holder: ItemViewHolder, el: CityForecast) {
        val icon = when (el.weather) {
            SUNNY -> R.drawable.ic_wb_sunny_black_24dp
            COLD -> R.drawable.ic_ac_unit_black_24dp
            STORM -> R.drawable.ic_flash_on_black_24dp
            CLOUDY -> R.drawable.ic_cloud_black_24dp
        }
        Glide.with(holder.cityImage.context)
            .load(icon)
            .into(holder.weatherImage)
    }

    inner class ItemViewHolder(item: ConstraintLayout) : RecyclerView.ViewHolder(item) {
        val cityName = item.findViewById<TextView>(R.id.cityName)
        val usefuleInfo = item.findViewById<TextView>(R.id.usefulInformation)
        val cityImage = item.findViewById<ImageView>(R.id.cityImage)
        val weatherImage = item.findViewById<ImageView>(R.id.weatherImage)
        val temperature = item.findViewById<TextView>(R.id.temperatureNumber)
        val units = item.findViewById<TextView>(R.id.temperatureUnits)
    }

    inner class HeaderViewHolder(section: FrameLayout) : RecyclerView.ViewHolder(section) {
        val title = section.findViewById<TextView>(R.id.title)
    }

    companion object {
        const val ITEM = 0
        const val HEADER = 1
    }
}