package com.epam.weatherforecastapp.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.epam.weatherforecastapp.R
import com.epam.weatherforecastapp.model.CityForecast
import com.epam.weatherforecastapp.model.Weather

/**
 * The first page of [ForecastPagerFragment].
 *
 * @see [ForecastPagerFragment]
 *
 * @author Vlad Korotkevich
 */

class FirstPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.item_first_page, container, false)

        val cityForecast = arguments?.getParcelable(ITEM) as CityForecast

        val weatherImage = view.findViewById<ImageView>(R.id.firstPageWeatherImage)
        val background = view.findViewById<ImageView>(R.id.collapseBackgroundImage)
        val temperature = view.findViewById<TextView>(R.id.firstPageTempNumber)
        val units = view.findViewById<TextView>(R.id.firstPageTempUnits)
        val cityName = view.findViewById<TextView>(R.id.firstPageCityName)

        loadBackground(background, cityForecast.image)
        loadWeatherImage(weatherImage, cityForecast.weather)
        cityName.text = cityForecast.cityName
        temperature.text = cityForecast.temperature.toString()
        units.text = cityForecast.units

        return view
    }

    private fun loadWeatherImage(image: ImageView, weather: Weather) {
        if (context == null) {
            image.setImageDrawable(resources.getDrawable(R.drawable.ic_placeholder_collaps, null))
            return
        }
        Glide.with(context ?: return)
            .load(weather.icon)
            .placeholder(R.drawable.ic_weather_icon_placeholder)
            .into(image)
    }

    private fun loadBackground(background: ImageView, url: String) {
        if (context == null) {
            background.setImageDrawable(resources.getDrawable(R.drawable.ic_placeholder_collaps, null))
            return
        }
        Glide.with(context ?: return)
            .load(url)
            .placeholder(R.drawable.ic_placeholder_collaps)
            .into(background)
    }

    companion object {
        private const val ITEM = "ITEM"

        fun newInstance(item: CityForecast) =
            FirstPageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ITEM, item)
                }
            }
    }

}