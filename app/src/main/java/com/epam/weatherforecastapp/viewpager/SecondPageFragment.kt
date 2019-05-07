package com.epam.weatherforecastapp.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.epam.weatherforecastapp.R
import com.epam.weatherforecastapp.model.CityForecast

/**
 * The second page of [ForecastPagerFragment].
 *
 * @see [ForecastPagerFragment]
 *
 * @author Vlad Korotkevich
 */

class SecondPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.item_second_page, container, false)
        val item = arguments?.getParcelable(ITEM) as CityForecast

        val cityInfo = view.findViewById<TextView>(R.id.secondPageCityInfo)
        cityInfo.text = item.info

        return view
    }

    companion object {
        private const val ITEM = "ITEM"

        fun newInstance(item: CityForecast) =
            SecondPageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ITEM, item)
                }
            }
    }
}
