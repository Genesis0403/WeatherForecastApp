package com.epam.weatherforecastapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.epam.weatherforecastapp.model.CityForecast
import com.epam.weatherforecastapp.recyclerview.ForecastFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivity, ForecastFragment.newInstance(CityForecast.list))
                .commit()
        }
    }

    private companion object {
        const val TAG = "Main Activity"
    }
}
