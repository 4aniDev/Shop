package ru.chani.shop.presentation.mainscreen.filters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.PopupWindow
import ru.chani.shop.R
import ru.chani.shop.databinding.FiltersBinding


class Filters(
    private val rootView: View,
    private val layoutInflater: LayoutInflater,
    private val context: Context,
) {

    private var _bindingFilter: FiltersBinding? = null
    private val bindingFilter: FiltersBinding
        get() = _bindingFilter ?: throw RuntimeException("CvFiltersBinding == null")

    private lateinit var popupWindow: PopupWindow

    fun showPopupWindow() {

        _bindingFilter = FiltersBinding.inflate(layoutInflater)

        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        val focusable = true

        popupWindow = PopupWindow(bindingFilter.root, width, height, focusable)
        popupWindow.elevation = FILTER_ELEVATION
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0)

        setBrands()
        setPrices()
        setSizes()

        setOnClickListeners()
    }

    private fun setBrands() {
        val array = arrayOf("Samsung", "Iphone", "Tolkun")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(context, R.layout.style_filters_spinner, array)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bindingFilter.spBrand.adapter = adapter
    }

    private fun setPrices() {
        val array = arrayOf("$300 - $500", "$500 - $1,000", "$2,000 - $10,000")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(context, R.layout.style_filters_spinner, array)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bindingFilter.spPrice.adapter = adapter
    }

    private fun setSizes() {
        val array = arrayOf("4.5 to 5.5 inches", "5.5 to 6.7 inches", "7 to 12 inches")
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(context, R.layout.style_filters_spinner, array)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bindingFilter.spSize.adapter = adapter
    }

    private fun setOnClickListeners() {
        bindingFilter.btDone.setOnClickListener { popupWindow.dismiss() }
        bindingFilter.btClose.setOnClickListener { popupWindow.dismiss() }
    }


    companion object {
        private const val FILTER_ELEVATION = 20F
    }

}