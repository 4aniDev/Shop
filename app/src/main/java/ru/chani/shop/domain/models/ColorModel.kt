package ru.chani.shop.domain.models

import android.graphics.Color

data class ColorModel(
    val colorString: String,
    var checkState: Boolean = UNCHECKED
) {
    val colorInt: Int = Color.parseColor(colorString)

    companion object {
        const val CHECKED = true
        const val UNCHECKED = false
    }
}
