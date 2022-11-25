package ru.chani.shop.presentation.productdetails.info.color

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.chani.shop.databinding.BtColorActiveBinding
import ru.chani.shop.databinding.BtColorInactiveBinding
import ru.chani.shop.domain.models.CapacityModel
import ru.chani.shop.domain.models.ColorModel

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.CustomViewHolder>() {

    var onColorItemClickListener: ((ColorModel) -> Unit)? = null

    private var listOfColors = emptyList<ColorModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = when (viewType) {
            ACTIVE -> {
                BtColorActiveBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            INACTIVE -> {
                BtColorInactiveBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            else -> throw RuntimeException("Unknown ViewType")
        }
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.root.backgroundTintList =
            ColorStateList.valueOf(listOfColors[position].colorInt)

        holder.binding.root.setOnClickListener {
            onColorItemClickListener?.invoke(listOfColors[position])
        }
    }

    override fun getItemCount(): Int {
        return listOfColors.size
    }

    override fun getItemViewType(position: Int): Int {
        if (listOfColors[position].checkState) {
            return ACTIVE
        } else {
            return INACTIVE
        }
    }

    fun setListOfColors(newList: List<ColorModel>) {
        val diffCallback = ColorDiffCallback(listOfColors, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listOfColors = newList
        diffResult.dispatchUpdatesTo(this)
    }


    inner class CustomViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val ACTIVE = 1
        private const val INACTIVE = 2
    }
}