package ru.chani.shop.presentation.mainscreen.category

import androidx.recyclerview.widget.DiffUtil
import ru.chani.shop.domain.models.CategoryModel

class CategoryDiffCallback(
    private val oldList: List<CategoryModel>,
    private val newList: List<CategoryModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCategory = oldList[oldItemPosition]
        val newCategory = newList[newItemPosition]
        return oldCategory.activeState == newCategory.activeState
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAd = oldList[oldItemPosition]
        val newAd = newList[newItemPosition]
        return oldAd == newAd
    }
}