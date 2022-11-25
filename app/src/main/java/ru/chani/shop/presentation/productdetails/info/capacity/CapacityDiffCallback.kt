package ru.chani.shop.presentation.productdetails.info.capacity

import androidx.recyclerview.widget.DiffUtil
import ru.chani.shop.domain.models.CapacityModel

class CapacityDiffCallback(
    private val oldList: List<CapacityModel>,
    private val newList: List<CapacityModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCategory = oldList[oldItemPosition]
        val newCategory = newList[newItemPosition]
        return oldCategory.checkState == newCategory.checkState
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAd = oldList[oldItemPosition]
        val newAd = newList[newItemPosition]
        return oldAd == newAd
    }
}