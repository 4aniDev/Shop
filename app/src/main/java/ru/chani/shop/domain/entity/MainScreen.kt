package ru.chani.shop.domain.entity

data class MainScreen(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)