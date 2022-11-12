package ru.chani.shop.domain.usecase

import ru.chani.shop.domain.Repository
import ru.chani.shop.domain.models.MainScreenModel

class GetMainScreenFields(private val repository: Repository) {
    suspend operator fun invoke(): MainScreenModel {
       return repository.getMainScreenModel()

    }
}