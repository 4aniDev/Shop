package ru.chani.shop.domain.usecase

import ru.chani.shop.domain.Repository
import ru.chani.shop.domain.models.LocationsModel

class GetLocations(private val repository: Repository) {
    operator fun invoke(): LocationsModel {
        return repository.getLocations()
    }
}