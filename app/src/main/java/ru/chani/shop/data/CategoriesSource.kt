package ru.chani.shop.data

import android.content.Context
import ru.chani.shop.R
import ru.chani.shop.domain.models.CategoryModel

class CategoriesSource(private val context: Context) {

    private val listOfCategories = listOf<CategoryModel>(
        CategoryModel(context.getString(R.string.phones), R.drawable.phone, activeState = CategoryModel.ACTIVE),
        CategoryModel(context.getString(R.string.computer), R.drawable.pc),
        CategoryModel(context.getString(R.string.health), R.drawable.health),
        CategoryModel(context.getString(R.string.books), R.drawable.book)
    )

    fun getListOfCategories(): List<CategoryModel> {
        return listOfCategories
    }
}