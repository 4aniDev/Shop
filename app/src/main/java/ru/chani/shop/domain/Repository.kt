package ru.chani.shop.domain

import ru.chani.shop.domain.models.CategoryModel
import ru.chani.shop.domain.models.LocationsModel
import ru.chani.shop.domain.models.MainScreenModel
import ru.chani.shop.domain.models.ProductModel

interface Repository {

    suspend fun getMainScreenModel(): MainScreenModel

    fun getListOfCategories(): List<CategoryModel>

    fun getLocations(): LocationsModel


    suspend fun getProductById(id: Int): ProductModel

    fun getListOfInfoCategoryTitles(): List<String>

}