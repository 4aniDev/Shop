package ru.chani.shop.domain.usecase

import ru.chani.shop.domain.Repository

class GetListOfInfoCategoryTitles(private val repository: Repository) {
    operator fun invoke() = repository.getListOfInfoCategoryTitles()
}