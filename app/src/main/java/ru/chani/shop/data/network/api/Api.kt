package ru.chani.shop.data.network.api

import retrofit2.http.GET
import ru.chani.shop.data.network.models.BestSellerAndHomeStore
import ru.chani.shop.data.network.models.Product

interface Api {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getBestSellerAndHomeStore(): BestSellerAndHomeStore

    @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProduct(): Product
}