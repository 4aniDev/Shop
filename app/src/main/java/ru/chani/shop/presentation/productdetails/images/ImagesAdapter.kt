package ru.chani.shop.presentation.productdetails.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.squareup.picasso.Picasso
import ru.chani.shop.databinding.CvBsItemFavoriteOffBinding
import ru.chani.shop.databinding.CvPdImageBinding
import ru.chani.shop.domain.models.BestSellerModel
import java.text.NumberFormat
import java.util.*

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.CustomViewHolder>() {

    var onBestSellerItemClickListener: ((BestSellerModel) -> Unit)? = null
    var onBestSellerItemFavoriteClickListener: ((BestSellerModel) -> Unit)? = null

    private var listOfImages = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = CvPdImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val imageBinding = holder.binding as CvPdImageBinding
        val currentImage = listOfImages[position % listOfImages.size]
        Picasso.get().load(currentImage).into(imageBinding.image)
    }

    override fun getItemCount(): Int {
        return SOMETHING_BIG_COUNT
    }


    fun setListOfImages(newList: List<String>) {
        val diffCallback = ImagesDiffCallback(listOfImages, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        diffResult.dispatchUpdatesTo(this)
        listOfImages = newList
    }


    inner class CustomViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val SOMETHING_BIG_COUNT = Integer.MAX_VALUE
        const val MIDDLE_OF_LIST = SOMETHING_BIG_COUNT / 2
    }

}