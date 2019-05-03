package com.epam.weatherforecastapp.recyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epam.weatherforecastapp.R
import com.epam.weatherforecastapp.model.CityForecast
import com.epam.weatherforecastapp.model.ForecastElement
import com.epam.weatherforecastapp.model.Header

class ForecastFragment : Fragment() {

    private lateinit var adapterOperator: ForecastOperator
    lateinit var adapter: ForecastAdapter
    var lastSortType = SortType.ALPH_ASC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val recycler = inflater.inflate(R.layout.weather_forecast_fragment, container, false) as RecyclerView

        val weatherForecast = arguments?.getParcelableArrayList(FORECAST_LIST) ?: mutableListOf<ForecastElement>()
        adapterOperator = ForecastOperator(weatherForecast)
        adapter = ForecastAdapter(weatherForecast, this)

        return recycler.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = this@ForecastFragment.adapter

            //adapterOperator.sort(lastSortType)
            val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(itemDecor)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.sort_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_alph_asc -> {
                adapterOperator.sort(SortType.ALPH_ASC)
                adapter.notifyDataSetChanged()
                lastSortType = SortType.ALPH_ASC
                return true
            }
            R.id.sort_alph_desc -> {
                adapterOperator.sort(SortType.ALPH_DESC)
                adapter.notifyDataSetChanged()
                lastSortType = SortType.ALPH_DESC
                return true
            }
            R.id.sort_temp_asc -> {
                adapterOperator.sort(SortType.TEMP_ASC)
                adapter.notifyDataSetChanged()
                lastSortType = SortType.TEMP_ASC
                return true
            }
            R.id.sort_temp_desc -> {
                adapterOperator.sort(SortType.TEMP_DESC)
                adapter.notifyDataSetChanged()
                lastSortType = SortType.TEMP_DESC
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ForecastAdapter.DIALOG_REQUEST_CODE) {
                if (data != null) {
                    onAddRemoveRequest(data)
                }
            }
        }
    }

    private fun onAddRemoveRequest(data: Intent) {
        val isRemove = data.getBooleanExtra(ForecastDialog.OPERATION, false)
        val item: CityForecast? = data.getParcelableExtra(ForecastDialog.ITEM)
        Log.d(TAG, "LOOOOL")
        if (isRemove) {
            adapterOperator.apply {
                removeFromFavorites(item)
                sort(lastSortType)
            }
        } else {
            adapterOperator.apply {
                addToFavorites(item)
                sort(lastSortType)
            }
        }
        adapter.notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "FORECAST FRAGMENT"
        private const val FORECAST_LIST = "FORECAST_ITEM"
        private const val ALL = "ALL"

        fun newInstance(items: ArrayList<ForecastElement>) =
            ForecastFragment().apply {
                arguments = Bundle().apply {
                    items.add(0, Header(ALL))
                    putParcelableArrayList(FORECAST_LIST, items)
                }
            }
    }
}