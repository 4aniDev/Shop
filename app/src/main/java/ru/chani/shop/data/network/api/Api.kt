package ru.chani.shop.data.network.api

import retrofit2.http.GET
import ru.chani.shop.data.network.models.BestSellerAndHomeStore

interface Api {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getBestSellerAndHomeStore(): BestSellerAndHomeStore
}