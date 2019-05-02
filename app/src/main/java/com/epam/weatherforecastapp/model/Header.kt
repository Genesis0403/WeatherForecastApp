package com.epam.weatherforecastapp.model

import kotlinx.android.parcel.Parcelize

@Parcelize
class Header(
    val title: String,
    override val isHeader: Boolean = true
) : ForecastElement
