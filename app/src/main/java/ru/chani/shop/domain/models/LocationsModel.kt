package ru.chani.shop.domain.models

data class LocationsModel(val places: Array<String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LocationsModel

        if (!places.contentEquals(other.places)) return false

        return true
    }

    override fun hashCode(): Int {
        return places.contentHashCode()
    }
}