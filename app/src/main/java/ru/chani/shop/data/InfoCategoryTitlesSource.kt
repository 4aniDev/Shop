package ru.chani.shop.data

import android.content.Context
import ru.chani.shop.R

class InfoCategoryTitlesSource(private val context: Context) {

    private val list = listOf<String>(
        context.getString(R.string.shop),
        context.getString(R.string.details),
        context.getString(R.string.features)
    )

    fun getList() = list
}