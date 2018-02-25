package com.htxcsoft.module.android.knowledge.module.android.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.htxcsoft.module.android.knowledge.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_dispatch_event2.*

class ActivityDispatchEventActivity : AppCompatActivity(), View.OnTouchListener, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispatch_event2)

        my_btn.setOnTouchListener(this)
        my_btn.setOnClickListener(this)

        my_ll.setOnClickListener(this)
        my_ll.setOnTouchListener(this)
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        Logger.d("OnTouchListener -- onTouch -- action ${event!!.action} -- $v")
        //return true  //onclick 失效
        return false
    }


    override fun onClick(v: View?) {
        Logger.d("OnTouchListener -- onclick -- $v")
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        Logger.e("ActivityDispatchEventActivity -- dispatchTouchEvent -- action ${ev!!.action}")

        //return false 如果返回false/true 则事件无法继续分发

        //return true

        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Logger.e("ActivityDispatchEventActivity -- onTouchEvent -- action ${event!!.action}")

        return super.onTouchEvent(event)
    }
}
