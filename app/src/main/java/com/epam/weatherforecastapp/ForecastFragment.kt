package com.epam.weatherforecastapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epam.weatherforecastapp.model.ForecastElement
import com.epam.weatherforecastapp.model.Header

class ForecastFragment : Fragment() {

    private lateinit var sorter: ForecastSorter
    private lateinit var adapter: ForecastAdapter

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

        val weatherForecast = attributes.getParcelableArrayList<ForecastElement>(FORECAST_LIST)
        sorter = ForecastSorter(weatherForecast)
        adapter = ForecastAdapter(weatherForecast)

        recycler.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = this@ForecastFragment.adapter

            val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(itemDecor)
        }
        return recycler
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.sort_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sort_alph_asc -> {
                sorter.sort(SortType.ALPH_ASC)
                adapter.notifyDataSetChanged()
                return true
            }
            R.id.sort_alph_desc -> {
                sorter.sort(SortType.ALPH_DESC)
                adapter.notifyDataSetChanged()
                return true
            }
            R.id.sort_temp_asc -> {
                sorter.sort(SortType.TEMP_ASC)
                adapter.notifyDataSetChanged()
                return true
            }
            R.id.sort_temp_desc -> {
                sorter.sort(SortType.TEMP_DESC)
                adapter.notifyDataSetChanged()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val FORECAST_LIST = "FORECAST_ITEM"
        private const val ALL = "ALL"
        val attributes = Bundle()

        fun newInstance(items: ArrayList<ForecastElement>) =
            ForecastFragment().apply {
                items.add(0, Header(ALL))
                attributes.putParcelableArrayList(FORECAST_LIST, items)
        }
    }
}