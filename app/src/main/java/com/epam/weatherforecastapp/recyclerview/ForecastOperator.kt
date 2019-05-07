package com.epam.weatherforecastapp.recyclerview

import android.util.Log
import com.epam.weatherforecastapp.model.CityForecast
import com.epam.weatherforecastapp.model.ForecastElement
import com.epam.weatherforecastapp.model.Header
import java.lang.IllegalArgumentException

/**
 * Utility class which helps with sorting,
 * adding and removing items from [ForecastAdapter]
 *
 * @author Vlad Korotkevich
 */

class ForecastOperator(private val forecastElements: MutableList<ForecastElement>) {

    private val favorites = forecastElements.asSequence()
        .filter { it !is Header }
        .map { it as CityForecast }
        .filter { it.isFavorite }
        .toMutableList()

    private val all = forecastElements.asSequence()
        .filter { it !is Header }
        .map { it as CityForecast }
        .toMutableList()

    private val favoritesHeader = Header(FAVORITE)
    private val allHeader = Header(ALL)

    fun addToFavorites(item: CityForecast?) {
        if (item == null) throw IllegalArgumentException("Item can't be null")
        if (!forecastElements.contains(item)) throw IllegalArgumentException("No such item: $item")
        if (item.isFavorite) throw IllegalArgumentException("$item is already in favorites")
        item.isFavorite = true
        favorites.add(item)
    }

    fun removeFromFavorites(item: CityForecast?) {
        if (item == null) throw IllegalArgumentException()
        if (!favorites.contains(item)) throw IllegalArgumentException("No such item: $item")
        if (!item.isFavorite) throw IllegalArgumentException("$item is not favorite")
        item.isFavorite = false
        favorites.remove(item)
    }

    fun sort(type: SortType) {
        when (type) {
            SortType.ALPH_ASC -> {
                all.sortBy { it.cityName }
                favorites.sortBy { it.cityName }
            }
            SortType.ALPH_DESC -> {
                all.sortByDescending { it.cityName }
                favorites.sortByDescending { it.cityName }
            }
            SortType.TEMP_ASC -> {
                all.sortBy { it.temperature }
                favorites.sortBy { it.temperature }
            }
            SortType.TEMP_DESC -> {
                all.sortByDescending { it.temperature }
                favorites.sortByDescending { it.temperature }
            }
        }
        forecastElements.apply {
            clear()
            if (favorites.isNotEmpty()) {
                add(favoritesHeader)
                addAll(favorites)
            }
            add(allHeader)
            addAll(all)
        }
    }

    companion object {
        const val TAG = "ForecastOperator"
        const val FAVORITE = "Favorite"
        const val ALL = "All"
    }
}