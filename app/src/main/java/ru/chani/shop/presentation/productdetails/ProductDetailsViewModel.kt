package ru.chani.shop.presentation.productdetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.chani.shop.data.RepositoryImpl
import ru.chani.shop.domain.models.CapacityModel
import ru.chani.shop.domain.models.ColorModel
import ru.chani.shop.domain.models.ProductModel
import ru.chani.shop.domain.usecase.GetListOfInfoCategoryTitles
import ru.chani.shop.domain.usecase.GetProductById

class ProductDetailsViewModel(context: Context, productId: Int) : ViewModel() {

    private val repository = RepositoryImpl(context = context)
    private val getProductById = GetProductById(repository)
    private val getListOfInfoCategoryTitles = GetListOfInfoCategoryTitles(repository)


    private val _product: MutableLiveData<ProductModel> = MutableLiveData()
    val product: LiveData<ProductModel>
        get() = _product

    private val _categoryTitles: MutableLiveData<List<String>> = MutableLiveData()
    val categoryTitles: LiveData<List<String>>
        get() = _categoryTitles

    private val _colorList: MutableLiveData<List<ColorModel>> = MutableLiveData()
    val colorList: LiveData<List<ColorModel>>
        get() = _colorList

    private val _capacityList: MutableLiveData<List<CapacityModel>> = MutableLiveData()
    val capacityList: LiveData<List<CapacityModel>>
        get() = _capacityList

    init {
        getProduct(productId)
        getCategoryTitles()
    }

    private fun getProduct(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val product = getProductById(id = id)
            _product.postValue(product)

            val colorList = mapColorFromProductToColorModel(product)
            _colorList.postValue(colorList)

            val capacityList = mapCapacityFromProductToCapacityModel(product)
            _capacityList.postValue(capacityList)
        }
    }

    private fun getCategoryTitles() {
        _categoryTitles.value = getListOfInfoCategoryTitles()
    }

    fun changeCheckedColor(colorModel: ColorModel) {
        val newList = mutableListOf<ColorModel>()
        colorList.value?.forEach {

            if (it == colorModel) {
                newList.add(
                    ColorModel(
                        colorString = it.colorString,
                        checkState = ColorModel.CHECKED
                    )
                )
            } else {
                newList.add(
                    ColorModel(
                        colorString = it.colorString,
                        checkState = ColorModel.UNCHECKED
                    )
                )
            }

        }
        _colorList.value = newList
    }

    fun changeCheckedCapacity(capacityModel: CapacityModel) {
        val newList = mutableListOf<CapacityModel>()
        capacityList.value?.forEach {

            if (it == capacityModel) {
                newList.add(
                    CapacityModel(
                        capacityString = it.capacityString,
                        checkState = ColorModel.CHECKED
                    )
                )
            } else {
                newList.add(
                    CapacityModel(
                        capacityString = it.capacityString,
                        checkState = ColorModel.UNCHECKED
                    )
                )
            }

        }
        _capacityList.value = newList
    }

    private fun mapColorFromProductToColorModel(product: ProductModel): List<ColorModel> {
        val listOfColors = mutableListOf<ColorModel>()
        product.color.forEach { listOfColors.add(ColorModel(colorString = it)) }

        //  Set first color is checked
        if (listOfColors.isNotEmpty()) {
            listOfColors[0].checkState = CapacityModel.CHECKED
        }

        return listOfColors
    }

    private fun mapCapacityFromProductToCapacityModel(product: ProductModel): List<CapacityModel> {
        val listOfCapacities = mutableListOf<CapacityModel>()
        product.capacity.forEach { listOfCapacities.add(CapacityModel(capacityString = it)) }

        //  Set first element is checked
        if (listOfCapacities.isNotEmpty()) {
            listOfCapacities[0].checkState = CapacityModel.CHECKED
        }

        return listOfCapacities
    }
}