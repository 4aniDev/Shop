package ru.chani.shop.domain.models

data class MainScreenModel(
    var best_seller: List<BestSellerModel>,
    val home_store: List<HomeStoreModel>
)
