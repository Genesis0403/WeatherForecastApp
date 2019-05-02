package com.epam.weatherforecastapp.model

import android.os.Parcelable

interface ForecastElement : Parcelable {
    val isHeader: Boolean
}