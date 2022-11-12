package ru.chani.shop.domain

import ru.chani.shop.domain.models.CategoryModel
import ru.chani.shop.domain.models.LocationsModel
import ru.chani.shop.domain.models.MainScreenModel

interface Repository {

    suspend fun getMainScreenModel(): MainScreenModel

    fun getListOfCategories(): List<CategoryModel>

    fun getLocations(): LocationsModel

}