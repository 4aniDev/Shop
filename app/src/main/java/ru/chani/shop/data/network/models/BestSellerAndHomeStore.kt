package ru.chani.shop.data.network.models

data class BestSellerAndHomeStore(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)