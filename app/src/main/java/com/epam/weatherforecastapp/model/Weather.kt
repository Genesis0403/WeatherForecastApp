package com.epam.weatherforecastapp.model

import com.epam.weatherforecastapp.R
import com.epam.weatherforecastapp.recyclerview.ForecastAdapter

/**
 * Enum which lets define an icon for [ForecastAdapter.ItemViewHolder].
 *
 * @author Vlad Korotkevich
 */
enum class Weather(val icon: Int) {
    SUNNY(R.drawable.ic_wb_sunny_black_24dp),
    COLD(R.drawable.ic_ac_unit_black_24dp),
    STORM(R.drawable.ic_flash_on_black_24dp),
    CLOUDY(R.drawable.ic_cloud_black_24dp)
}