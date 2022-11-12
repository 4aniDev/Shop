package ru.chani.shop.presentation.mainscreen.bestseller

import androidx.recyclerview.widget.DiffUtil
import ru.chani.shop.domain.models.BestSellerModel

class BestSellerDiffCallback(
    private val oldList: List<BestSellerModel>,
    private val newList: List<BestSellerModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldBestSeller = oldList[oldItemPosition]
        val newBestSeller = newList[newItemPosition]
        return oldBestSeller.is_favorites == newBestSeller.is_favorites
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAd = oldList[oldItemPosition]
        val newAd = newList[newItemPosition]
        return oldAd == newAd
    }
}