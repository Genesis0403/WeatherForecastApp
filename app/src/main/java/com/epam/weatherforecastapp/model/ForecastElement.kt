package com.epam.weatherforecastapp.model

import android.os.Parcelable

/**
 * Interface which let to define an item or header.
 *
 * @see [CityForecast]
 * @see [Header]
 *
 * @author Vlad Korotkevich
 */
interface ForecastElement : Parcelable {
    val isHeader: Boolean
}