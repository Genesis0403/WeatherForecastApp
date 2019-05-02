package com.epam.weatherforecastapp

import android.util.Log
import com.epam.weatherforecastapp.model.CityForecast
import com.epam.weatherforecastapp.model.ForecastElement
import com.epam.weatherforecastapp.model.Header

class ForecastSorter(val forecastElements: MutableList<ForecastElement>) {

    private val all = forecastElements.asSequence()
        .filter { it !is Header }
        .map { it as CityForecast }
        .toMutableList()

    fun sort(type: SortType) {
        Log.d(TAG, "WE ARE IN!!!")
        when (type) {
            SortType.ALPH_ASC -> {
                all.sortBy { it.cityName }
            }
            SortType.ALPH_DESC -> {
                all.sortByDescending { it.cityName }
            }
            SortType.TEMP_ASC -> {
                all.sortBy { it.temperature }
            }
            SortType.TEMP_DESC -> {
                all.sortByDescending { it.temperature }
            }
        }
        forecastElements.apply {
            clear()
            val favorites = all.asSequence().filter { it.isFavorite }.toList()
            if (favorites.isNotEmpty()) {
                add(Header(FAVORITE))
                addAll(favorites)
            }
            add(Header(ALL))
            addAll(all)
        }
    }

    companion object {
        const val TAG = "ForecastSorter"
        const val FAVORITE = "Favorite"
        const val ALL = "All"
    }
}