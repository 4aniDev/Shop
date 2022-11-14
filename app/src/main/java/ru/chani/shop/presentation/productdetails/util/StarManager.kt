package ru.chani.shop.presentation.productdetails.util

import android.widget.ImageView
import ru.chani.shop.R

class StarManager(private val view: ViewHasStar) {


    fun setStarsByRating(rating: Double) {

        with(view) {
            if (rating > 0.0 && rating <= 0.5) {
                setHalfStar(ivStar1)
            }

            if (rating > 0.5 && rating <= 1.0) {
                setFullStar(ivStar1)
            }

            if (rating > 1.0 && rating <= 1.5) {
                setFullStar(ivStar1)
                setHalfStar(ivStar2)
            }

            if (rating > 1.5 && rating <= 2.0) {
                setFullStar(ivStar1)
                setFullStar(ivStar2)
            }

            if (rating > 2.0 && rating <= 2.5) {
                setFullStar(ivStar1)
                setFullStar(ivStar2)
                setHalfStar(ivStar3)
            }

            if (rating > 2.5 && rating <= 3.0) {
                setFullStar(ivStar1)
                setFullStar(ivStar2)
                setFullStar(ivStar3)
            }

            if (rating > 3.0 && rating <= 3.5) {
                setFullStar(ivStar1)
                setFullStar(ivStar2)
                setFullStar(ivStar3)
                setHalfStar(ivStar4)
            }

            if (rating > 3.5 && rating <= 4.0) {
                setFullStar(ivStar1)
                setFullStar(ivStar2)
                setFullStar(ivStar3)
                setFullStar(ivStar4)
            }

            if (rating > 4.0 && rating <= 4.5) {
                setFullStar(ivStar1)
                setFullStar(ivStar2)
                setFullStar(ivStar3)
                setFullStar(ivStar4)
                setHalfStar(ivStar5)
            }

            if (rating > 4.5 && rating <= 5.0) {
                setFullStar(ivStar1)
                setFullStar(ivStar2)
                setFullStar(ivStar3)
                setFullStar(ivStar4)
                setFullStar(ivStar5)
            }

        }


    }


    private fun setHalfStar(iv: ImageView) {
        iv.setImageResource(R.drawable.star_half)
    }

    private fun setFullStar(iv: ImageView) {
        iv.setImageResource(R.drawable.star)
    }

}