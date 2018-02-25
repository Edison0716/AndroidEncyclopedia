package com.htxcsoft.module.android.knowledge.module.android.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import com.orhanobut.logger.Logger

/**
 * @author: 巴黎没有摩天轮Li
 * @description:
 * @date: Created in 上午9:50 2018/2/25
 * @modified by:
 */
class DispatchTouchLinearLayout : LinearLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        Logger.d("DispatchTouchLinearLayout -- dispatchTouchEvent -- action ${ev!!.action}")

        return super.dispatchTouchEvent(ev)
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        Logger.d("DispatchTouchLinearLayout -- onInterceptTouchEvent -- action ${ev!!.action}")
        return super.onInterceptTouchEvent(ev)
        //return true
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Logger.d("DispatchTouchLinearLayout -- onTouchEvent -- action ${event!!.action}")

        return super.onTouchEvent(event)
    }
}
