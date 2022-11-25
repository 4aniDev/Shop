package ru.chani.shop.domain.models

data class CapacityModel(
    val capacityString: String,
    var checkState: Boolean = UNCHECKED
) {

    companion object {
        const val CHECKED = true
        const val UNCHECKED = false
    }
}
