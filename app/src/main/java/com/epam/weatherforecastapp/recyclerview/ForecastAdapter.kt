package com.epam.weatherforecastapp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.epam.weatherforecastapp.R
import com.epam.weatherforecastapp.model.CityForecast
import com.epam.weatherforecastapp.model.ForecastElement
import com.epam.weatherforecastapp.model.Header
import com.epam.weatherforecastapp.viewpager.ForecastPagerFragment

class ForecastAdapter(
    private val items: List<ForecastElement>,
    private val fragment: ForecastFragment
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
        val el = items[holder.adapterPosition]
        if (holder is ItemViewHolder) {
            el as CityForecast
            holder.cityName.text = el.cityName
            holder.usefulInfo.text = el.info
            holder.temperature.text = el.temperature.toString()
            holder.units.text = el.units
            loadWeatherIcon(holder, el)

            Glide.with(holder.cityImage.context)
                .load(el.image)
                .placeholder(R.drawable.ic_weather_icon_placeholder)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.cityImage)

            holder.itemView.setOnClickListener {
                //Log.d(TAG, el.toString())
                onClickListenerInit(el)
            }

            holder.itemView.setOnLongClickListener {
                onLongClickListenerInit(holder)
            }

        } else {
            holder as HeaderViewHolder
            el as Header
            holder.title.text = el.title
        }
    }

    private fun onClickListenerInit(item: CityForecast) {
        fragment.activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.mainActivity, ForecastPagerFragment.newInstance(item))
            addToBackStack(null)
            commit()
        }
    }

    private fun onLongClickListenerInit(holder: ItemViewHolder): Boolean {
        val adapterPosition = holder.adapterPosition
        return if (adapterPosition != RecyclerView.NO_POSITION) {
            ForecastDialog.newInstance(items[adapterPosition] as CityForecast)
                .apply {
                    setTargetFragment(
                        fragment,
                        DIALOG_REQUEST_CODE
                    )
                }.show(fragment.activity?.supportFragmentManager ?: return false, null)
            true
        } else {
            false
        }
    }

    private fun loadWeatherIcon(holder: ItemViewHolder, el: CityForecast) {
        Glide.with(holder.cityImage.context)
            .load(el.weather.icon)
            .placeholder(R.drawable.ic_weather_icon_placeholder)
            .into(holder.weatherImage)
    }

    inner class ItemViewHolder(item: ConstraintLayout) : RecyclerView.ViewHolder(item) {
        val cityName: TextView = item.findViewById(R.id.cityName)
        val usefulInfo: TextView = item.findViewById(R.id.usefulInformation)
        val cityImage: ImageView = item.findViewById(R.id.cityImage)
        val weatherImage: ImageView = item.findViewById(R.id.firstPageWeatherImage)
        val temperature: TextView = item.findViewById(R.id.firstPageTempNumber)
        val units: TextView = item.findViewById(R.id.firstPageTempUnits)
    }

    inner class HeaderViewHolder(header: FrameLayout) : RecyclerView.ViewHolder(header) {
        val title: TextView = header.findViewById(R.id.title)
    }

    companion object {
        private const val TAG = "FORECAST ADAPTER"
        const val ITEM = 0
        const val HEADER = 1
        const val DIALOG_REQUEST_CODE = 666
    }
}