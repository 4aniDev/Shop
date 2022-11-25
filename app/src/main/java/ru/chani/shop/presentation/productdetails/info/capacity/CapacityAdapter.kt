package ru.chani.shop.presentation.productdetails.info.capacity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.chani.shop.databinding.BtCapacityActiveBinding
import ru.chani.shop.databinding.BtCapacityInactiveBinding
import ru.chani.shop.domain.models.CapacityModel

class CapacityAdapter : RecyclerView.Adapter<CapacityAdapter.CustomViewHolder>() {

    var onCapacityItemClickListener: ((CapacityModel) -> Unit)? = null

    private var listOfCapacities = emptyList<CapacityModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = when (viewType) {
            ACTIVE -> {
                BtCapacityActiveBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            INACTIVE -> {
                BtCapacityInactiveBinding.inflate(
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

        when(holder.binding) {
            is BtCapacityActiveBinding -> {
                holder.binding.tvCapacity.text = listOfCapacities[position].capacityString
            }
            is BtCapacityInactiveBinding -> {
                holder.binding.tvCapacity.text = listOfCapacities[position].capacityString
            }

        }

        holder.binding.root.setOnClickListener {
            onCapacityItemClickListener?.invoke(listOfCapacities[position])
        }
    }

    override fun getItemCount(): Int {
        return listOfCapacities.size
    }

    override fun getItemViewType(position: Int): Int {
        if (listOfCapacities[position].checkState) {
            return ACTIVE
        } else {
            return INACTIVE
        }
    }

    fun setListOfCapacities(newList: List<CapacityModel>) {
        val diffCallback = CapacityDiffCallback(listOfCapacities, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listOfCapacities = newList
        diffResult.dispatchUpdatesTo(this)
    }


    inner class CustomViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val ACTIVE = 1
        private const val INACTIVE = 2
    }
}