package com.example.ch12pratice

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout

class ExoPlayerMotionLayout
@JvmOverloads constructor(
    context : Context,
    attributeSet : AttributeSet,
    defStyleaAttr: Int = 0
    ): MotionLayout(context, attributeSet, defStyleaAttr){


    var targetView : View? = null
    private val gestureDetector  by lazy {
        GestureDetector(context, object  : GestureDetector.SimpleOnGestureListener(){
            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                return
            }
        })
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        return
    }
}