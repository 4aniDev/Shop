package ru.chani.shop.presentation.productdetails.info.color

import androidx.recyclerview.widget.DiffUtil
import ru.chani.shop.domain.models.ColorModel

class ColorDiffCallback(
    private val oldList: List<ColorModel>,
    private val newList: List<ColorModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldColor = oldList[oldItemPosition]
        val newColor = newList[newItemPosition]
        return oldColor.checkState == newColor.checkState
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAd = oldList[oldItemPosition]
        val newAd = newList[newItemPosition]
        return oldAd == newAd
    }
}