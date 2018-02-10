package com.htxcsoft.module.android.knowledge.module.android_view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import com.htxcsoft.module.android.knowledge.R
import kotlinx.android.synthetic.main.activity_follow_view.*

class FollowViewActivity : AppCompatActivity() {
    private var dx: Float = 0.0f
    private var dy: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow_view)

        bt_target.setOnTouchListener { v, motionEvent ->
            if (motionEvent!!.action == MotionEvent.ACTION_DOWN) {
                dx = motionEvent.x
                dy = motionEvent.y
            } else if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                v!!.x = motionEvent.rawX - dx
                v.y = motionEvent.rawY - dy
            }
            true
        }
    }
}
