package com.htxcsoft.module.android.knowledge.module.android.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button

import com.orhanobut.logger.Logger

/**
 * @author: 巴黎没有摩天轮Li
 * @description:
 * @date: Created in 上午10:05 2018/2/25
 * @modified by:
 */
@SuppressLint("AppCompatCustomView")
class DispatchTouchButton : Button {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {

        Logger.d("DispatchTouchButton -- dispatchTouchEvent -- action ${event!!.action}")

        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Logger.d("DispatchTouchButton -- onTouchEvent -- action ${event!!.action}")

        return super.onTouchEvent(event)
    }
}
