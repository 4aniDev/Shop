package ru.chani.shop.presentation.productdetails.info

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.chani.shop.domain.models.ProductModel
import ru.chani.shop.presentation.productdetails.ProductDetailsViewModel


class ProductInfoAdapter(
    private val product: ProductModel,
    private val productDetailsViewModel: ProductDetailsViewModel,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return InfoShopFragment.newInstance(
            product = product,
            productDetailsViewModel = productDetailsViewModel
        )
    }

    companion object {
        private const val ITEM_COUNT = 3
    }
}
