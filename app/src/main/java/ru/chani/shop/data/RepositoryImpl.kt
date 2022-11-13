package ru.chani.shop.data

import android.content.Context
import ru.chani.shop.data.network.NetworkMapper
import ru.chani.shop.data.network.api.RetrofitInstance
import ru.chani.shop.domain.Repository
import ru.chani.shop.domain.models.CategoryModel
import ru.chani.shop.domain.models.LocationsModel
import ru.chani.shop.domain.models.MainScreenModel
import ru.chani.shop.domain.models.ProductModel

class RepositoryImpl(context: Context) : Repository {

    private val categoriesSource = CategoriesSource(context)

    private val mapper = NetworkMapper()

    override suspend fun getMainScreenModel(): MainScreenModel {
        return mapper.bestSellerAndHomeStoreToMainScreenModel(
            RetrofitInstance.api.getBestSellerAndHomeStore()
        )
    }

    override fun getListOfCategories(): List<CategoryModel> {
        return categoriesSource.getListOfCategories()
    }

    override fun getLocations(): LocationsModel {
        return LocationsModel(places = arrayOf("Zihuatanejo, Gro", "Moscow, Russia"))
    }

    override suspend fun getProductById(id: Int): ProductModel {
        return mapper.productToProductModel(RetrofitInstance.api.getProduct())
    }
}