package ru.chani.shop.presentation.mainscreen.bestseller

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.squareup.picasso.Picasso
import ru.chani.shop.databinding.CvBsItemFavoriteOffBinding
import ru.chani.shop.databinding.CvBsItemFavoriteOnBinding
import ru.chani.shop.domain.models.BestSellerModel
import java.text.NumberFormat
import java.util.*

class BestSellerAdapter : RecyclerView.Adapter<BestSellerAdapter.CustomViewHolder>() {

    var onBestSellerItemClickListener: ((BestSellerModel) -> Unit)? = null
    var onBestSellerItemFavoriteClickListener: ((BestSellerModel) -> Unit)? = null

    private var listOfBestSellers = emptyList<BestSellerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = when (viewType) {
            FAVORITE -> {
                CvBsItemFavoriteOnBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            DISLIKED -> {
                CvBsItemFavoriteOffBinding.inflate(
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
        val currentBestSeller = listOfBestSellers[position]

        when (holder.binding) {

            is CvBsItemFavoriteOnBinding -> {
                with(holder.binding) {
                    tvTitle.text = currentBestSeller.title
                    tvDiscountPrice.text = getPrice(currentBestSeller.discount_price)
                    tvPriceWithoutDiscount.text = getPrice(currentBestSeller.price_without_discount)
                    tvPriceWithoutDiscount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    Picasso.get().load(currentBestSeller.picture).into(ivPicture)

                    //setListener
                    cvFavorite.setOnClickListener {
                        onBestSellerItemFavoriteClickListener?.invoke(currentBestSeller)
                    }
                }
            }

            is CvBsItemFavoriteOffBinding -> {
                with(holder.binding) {
                    tvTitle.text = currentBestSeller.title
                    tvDiscountPrice.text = getPrice(currentBestSeller.discount_price)
                    tvPriceWithoutDiscount.text = getPrice(currentBestSeller.price_without_discount)
                    tvPriceWithoutDiscount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    Picasso.get().load(currentBestSeller.picture).into(ivPicture)

                    //setListener
                    cvFavorite.setOnClickListener {
                        onBestSellerItemFavoriteClickListener?.invoke(currentBestSeller)
                    }
                }
            }
        }




        holder.binding.root.setOnClickListener {
            onBestSellerItemClickListener?.invoke(listOfBestSellers[position])
        }

    }

    override fun getItemCount(): Int {
        return listOfBestSellers.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (listOfBestSellers[position].is_favorites) {
            FAVORITE
        } else {
            DISLIKED
        }
    }

    fun setListOfBestSellers(newList: List<BestSellerModel>) {
        val diffCallback = BestSellerDiffCallback(listOfBestSellers, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        diffResult.dispatchUpdatesTo(this)
        listOfBestSellers = newList
    }


    private fun getPrice(int: Int): String {
        val long = int.toLong()
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("USD")

        return format.format(long)
    }

    inner class CustomViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val FAVORITE = 1
        private const val DISLIKED = 2
    }
}