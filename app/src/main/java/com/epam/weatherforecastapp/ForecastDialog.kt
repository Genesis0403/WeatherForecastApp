package com.epam.weatherforecastapp

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.epam.weatherforecastapp.model.CityForecast

class ForecastDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        val item: CityForecast? = arguments?.getParcelable(ITEM)
        val isFavorite = item?.isFavorite ?: throw IllegalArgumentException("Item can't be null")

        return builder.apply {
            val title = if (isFavorite) getString(R.string.remove_title) else getString(R.string.add_title)
            val buttonAction = if (isFavorite) getString(R.string.remove_action) else getString(R.string.add_action)
            val intent = Intent().apply {
                putExtra(OPERATION, item.isFavorite)
                putExtra(ITEM, item)
            }
            setTitle(title)
            setPositiveButton(buttonAction) { _, _ ->
                targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            }
         }.create()
    }

    companion object {
        const val ITEM = "ITEM"
        const val OPERATION = "OPERATION"

        fun newInstance(item: CityForecast) =
            ForecastDialog().apply {
                arguments = Bundle().apply {
                    putParcelable(ITEM, item)
                }
            }
    }
}