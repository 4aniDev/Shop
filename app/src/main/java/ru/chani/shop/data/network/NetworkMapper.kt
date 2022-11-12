package ru.chani.shop.data.network

import ru.chani.shop.data.network.models.BestSeller
import ru.chani.shop.data.network.models.BestSellerAndHomeStore
import ru.chani.shop.data.network.models.HomeStore
import ru.chani.shop.domain.models.BestSellerModel
import ru.chani.shop.domain.models.HomeStoreModel
import ru.chani.shop.domain.models.MainScreenModel

class NetworkMapper {

    fun bestSellerToBestSellerModel(bestSeller: BestSeller): BestSellerModel {
        return BestSellerModel(
            discount_price = bestSeller.discount_price,
            id = bestSeller.id,
            is_favorites = bestSeller.is_favorites,
            picture = bestSeller.picture,
            price_without_discount = bestSeller.price_without_discount,
            title = bestSeller.title
        )
    }

    fun homeStoreToHomeStoreModel(homeStore: HomeStore): HomeStoreModel {
        return HomeStoreModel(
            id = homeStore.id,
            is_buy = homeStore.is_buy,
            is_new = homeStore.is_new,
            picture = homeStore.picture,
            subtitle = homeStore.subtitle,
            title = homeStore.title
        )
    }

    fun bestSellerAndHomeStoreToMainScreenModel(bestSellerAndHomeStore: BestSellerAndHomeStore): MainScreenModel {
        return with(bestSellerAndHomeStore) {
            MainScreenModel(
                best_seller.map { bestSellerToBestSellerModel(it)},
                home_store.map { homeStoreToHomeStoreModel(it) }
            )
        }



    }
}