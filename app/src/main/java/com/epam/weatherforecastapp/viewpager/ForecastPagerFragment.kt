package com.epam.weatherforecastapp.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.epam.weatherforecastapp.R
import com.epam.weatherforecastapp.model.CityForecast

/**
 * Fragment which represents [ViewPager]
 * with [FirstPageFragment] and [SecondPageFragment].
 *
 * [ViewPagerAdapter] is used to scroll between pages
 *
 * @see [FirstPageFragment]
 * @see [SecondPageFragment]
 *
 * @author Vlad Korotkevich
 */

class ForecastPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val viewPager = inflater.inflate(R.layout.forecast_pager_fragment, container, false) as ViewPager
        Log.d(TAG, "WE ARE IN!!!")
        viewPager.adapter = ViewPagerAdapter(childFragmentManager)

        return viewPager
    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val item = arguments?.getParcelable(ITEM) as CityForecast
            Log.d(TAG, item.toString())
            return if (position == 0) {
                FirstPageFragment.newInstance(item)
            } else {
                SecondPageFragment.newInstance(item)
            }
        }

        override fun getCount(): Int {
            return PAGES_NUMBER
        }
    }

    companion object {
        const val PAGES_NUMBER = 2
        private const val ITEM = "ITEM"
        private const val TAG = "VIEWPAGER FRAGMENT"
        fun newInstance(item: CityForecast) =
            ForecastPagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ITEM, item)
                }
            }
    }
}
