package ru.chani.shop.presentation

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun goBack()

    fun goToProductDetails(id: Int)

}