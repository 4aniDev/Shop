package ru.chani.shop.domain.models

data class CategoryModel(
    val title: String,
    val resId: Int,
    var activeState: Boolean = INACTIVE
) {
    companion object {
        const val ACTIVE = true
        const val INACTIVE = false
    }
}