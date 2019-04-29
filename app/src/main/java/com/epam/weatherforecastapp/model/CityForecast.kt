package com.epam.weatherforecastapp.model

import android.net.Uri

data class CityForecast(
    val cityName: String,
    val info: String,
    val temperature: Int,
    val units: String,
    val image: Uri,
    val weather: Weather
) : ForecastElement() {

    var isFavorite: Boolean = false

    companion object {
        val list = listOf<CityForecast>(

        )
    }
}