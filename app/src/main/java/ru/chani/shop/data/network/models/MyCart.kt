package ru.chani.shop.data.network.models

data class MyCart(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)