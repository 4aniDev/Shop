package ru.chani.shop.domain.entity

data class MyCart(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)