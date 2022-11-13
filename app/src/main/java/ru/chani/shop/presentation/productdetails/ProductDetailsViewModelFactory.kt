package ru.chani.shop.presentation.productdetails

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductDetailsViewModelFactory(
    private val context: Context,
    private val productId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel(context, productId) as T
    }
}