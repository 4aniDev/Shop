package ru.chani.shop.presentation.mainscreen.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.chani.shop.databinding.LlCategoryActiveBinding
import ru.chani.shop.databinding.LlCategoryInactiveBinding
import ru.chani.shop.domain.models.CategoryModel

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CustomViewHolder>() {

    var onCategoryItemClickListener: ((CategoryModel) -> Unit)? = null

    private var listOfCategories = emptyList<CategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = when (viewType) {
            ACTIVE -> {
                LlCategoryActiveBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            INACTIVE -> {
                LlCategoryInactiveBinding.inflate(
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
        when (holder.binding) {
            is LlCategoryActiveBinding -> {
                holder.binding.tvTitle.text = listOfCategories[position].title
                holder.binding.included.ivIcon.setImageResource(listOfCategories[position].resId)
            }

            is LlCategoryInactiveBinding -> {
                holder.binding.tvTitle.text = listOfCategories[position].title
                holder.binding.included.ivIcon.setImageResource(listOfCategories[position].resId)
            }
        }


        holder.binding.root.setOnClickListener {
            onCategoryItemClickListener?.invoke(listOfCategories[position])
        }
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }

    override fun getItemViewType(position: Int): Int {
        if (listOfCategories[position].activeState) {
            return ACTIVE
        } else {
            return INACTIVE
        }
    }

    fun setListOfCategories(newList: List<CategoryModel>) {
        val diffCallback = CategoryDiffCallback(listOfCategories, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listOfCategories = newList
        diffResult.dispatchUpdatesTo(this)
    }


    inner class CustomViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val ACTIVE = 1
        private const val INACTIVE = 2
    }
}