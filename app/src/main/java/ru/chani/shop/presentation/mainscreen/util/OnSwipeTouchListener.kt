package ru.chani.shop.presentation.mainscreen.util

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs


class OnSwipeTouchListener(
    context: Context,
    rightSwipe: ActionOnRightSwipe
) : OnTouchListener {

    private var gestureDetector: GestureDetector =
        GestureDetector(context, GestureListener(rightSwipe))


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }


    private class GestureListener(private val rightSwipe: ActionOnRightSwipe) :
        SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val distanceX = e2.x - e1.x
            val distanceY = e2.y - e1.y
            if (abs(distanceX) > abs(distanceY) && abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && abs(
                    velocityX
                ) > SWIPE_VELOCITY_THRESHOLD
            ) {
                if (distanceX > 0) {
                    rightSwipe.rightSwipe()
                } else {
                    onSwipeLeft()
                }
                return true
            }
            return false
        }

        fun onSwipeLeft() {}


        companion object {
            private const val SWIPE_DISTANCE_THRESHOLD = 100
            private const val SWIPE_VELOCITY_THRESHOLD = 100
        }
    }
}