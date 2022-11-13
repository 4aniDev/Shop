package ru.chani.shop.presentation.productdetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.chani.shop.data.RepositoryImpl
import ru.chani.shop.domain.models.ProductModel
import ru.chani.shop.domain.usecase.GetProductById

class ProductDetailsViewModel(context: Context, productId: Int) : ViewModel() {

    private val repository = RepositoryImpl(context = context)
    private val getProductById = GetProductById(repository)


    private val _product: MutableLiveData<ProductModel> = MutableLiveData()
    val product: LiveData<ProductModel>
        get() = _product

    init {
        getProduct(productId)
    }

    private fun getProduct(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _product.postValue(getProductById(id = id))
        }
    }
}