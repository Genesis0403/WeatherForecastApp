package com.epam.weatherforecastapp.model

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.parcel.Parcelize

/**
 * Header for [RecyclerView].
 *
 * @author Vlad Korotkevich
 */

@Parcelize
class Header(
    val title: String,
    override val isHeader: Boolean = true
) : ForecastElement
