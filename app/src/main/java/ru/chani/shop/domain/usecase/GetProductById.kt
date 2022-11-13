package ru.chani.shop.domain.usecase

import ru.chani.shop.domain.Repository
import ru.chani.shop.domain.models.ProductModel

class GetProductById(private val repository: Repository) {
    suspend operator fun invoke(id: Int) = repository.getProductById(id)
}