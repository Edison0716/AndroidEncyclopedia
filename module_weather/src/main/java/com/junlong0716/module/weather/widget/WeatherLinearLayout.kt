package com.junlong0716.module.weather.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.LinearLayout

/**
 * @author: 巴黎没有摩天轮Li
 * @description:
 * @date: Created in 下午2:05 2018/1/3
 * @modified by:
 */

class WeatherLinearLayout : LinearLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (childCount >= 2) {
            val parent = parent
            if (parent != null) {
                val height = (parent as ViewGroup).measuredHeight
                if (height > 0) {
                    val firstChild = getChildAt(0)
                    val firstParams = firstChild.layoutParams as LinearLayout.LayoutParams
                    firstParams.height = height * 4 / 5
                    firstChild.layoutParams = firstParams
                    val secondChild = getChildAt(1)
                    val secondParams = secondChild.layoutParams as LinearLayout.LayoutParams
                    secondParams.height = height * 1 / 5
                    secondChild.layoutParams = secondParams
                }
            }
        }
    }

}
