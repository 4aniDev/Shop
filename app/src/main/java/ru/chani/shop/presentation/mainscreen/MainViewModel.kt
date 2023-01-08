package ru.chani.shop.presentation.mainscreen

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.chani.shop.data.RepositoryImpl
import ru.chani.shop.domain.models.*
import ru.chani.shop.domain.usecase.GetListOfCategories
import ru.chani.shop.domain.usecase.GetLocations
import ru.chani.shop.domain.usecase.GetMainScreenFields

class MainViewModel(context: Context) : ViewModel() {

    private val repository = RepositoryImpl(context = context)
    private val getMainScreenFields = GetMainScreenFields(repository = repository)
    private val getListOfCategories = GetListOfCategories(repository = repository)
    private val getLocations = GetLocations(repository = repository)

    private var currentIndexOfHomeStore = DEFAULT_HOME_STORE_INDEX

    val mainScreen: MutableLiveData<MainScreenModel> = MutableLiveData()
    val currentHomeStore: MutableLiveData<HomeStoreModel> = MutableLiveData()
    val bestSeller: MutableLiveData<List<BestSellerModel>> = MutableLiveData()
    val categories: MutableLiveData<List<CategoryModel>> = MutableLiveData()
    val locations: MutableLiveData<LocationsModel> = MutableLiveData()

    init {
        getMainScreen()
        getCategories()
        getPositions()
    }

    fun getMainScreen() {
        val a = viewModelScope.launch {
            val response = getMainScreenFields()
            mainScreen.value = response

            currentHomeStore.value = response.home_store[DEFAULT_HOME_STORE_INDEX]
            bestSeller.value = response.best_seller
        }
    }

    fun getCategories() {
        categories.value = getListOfCategories()
    }

    fun changeActiveCategory(category: CategoryModel) {
        val newList = mutableListOf<CategoryModel>()
        categories.value?.forEach {

            if (it == category) {
                newList.add(CategoryModel(it.title, it.resId, CategoryModel.ACTIVE))
            } else {
                newList.add(CategoryModel(it.title, it.resId, CategoryModel.INACTIVE))
            }

        }
        categories.value = newList
    }

    fun nextHomeStore() {
        mainScreen.value?.let { mainScreenModel ->

            if (++currentIndexOfHomeStore < mainScreenModel.home_store.size) {
                currentHomeStore.value = mainScreenModel.home_store[currentIndexOfHomeStore]
            } else {
                currentIndexOfHomeStore = DEFAULT_HOME_STORE_INDEX
                currentHomeStore.value = mainScreenModel.home_store[currentIndexOfHomeStore]
            }
        }
    }

    fun getPositions() {
        locations.value = getLocations()
    }

    fun changeBestSellerItemFavoriteState(bestSellerModel: BestSellerModel) {
        val newList = mutableListOf<BestSellerModel>()

        bestSeller.value?.forEach {
            if (bestSellerModel == it) {
                //  change state

                newList.add(it.copy(is_favorites = !bestSellerModel.is_favorites))
            } else {

                newList.add(it)
            }
        }

        bestSeller.value = newList
    }


    companion object {
        private const val DEFAULT_HOME_STORE_INDEX = 0
    }
}