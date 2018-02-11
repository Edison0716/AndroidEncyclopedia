package com.htxcsoft.module.android.knowledge.module.android_view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.htxcsoft.module.android.knowledge.MainActivity
import com.htxcsoft.module.android.knowledge.R
import kotlinx.android.synthetic.main.activity_follow_view.*

/*
* getRawX 是 view 距离 外侧屏幕 x 坐标
* getRawY 是 view 距离 外侧屏幕 y 坐标 注意 状态栏 与 自定义 标题栏除外
* getX getY 是 view 相对于 ViewGroup 左上角坐标
* translation X Y 是 移动之后 getX getY 之间的差值 也就是移动的距离
*
* https://juejin.im/post/5a7bb25e5188257a5911b06a?utm_source=gold_browser_extension
* */
class FollowViewActivity : AppCompatActivity(), View.OnTouchListener {
    private var dx: Float = 0.0f
    private var dy: Float = 0.0f
    private lateinit var mGestureDetector: GestureDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow_view)
        mGestureDetector = GestureDetector(this, GestureListener())
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
        bt_target.setOnTouchListener(this)
        bt_target.isFocusable = true
        bt_target.isClickable = true
        bt_target.isLongClickable = true
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return mGestureDetector.onTouchEvent(event)
    }

    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        /*
         * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
         * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
         *
         * 而onDown也是由一个MotionEventACTION_DOWN触发的，但是他没有任何限制，
         * 也就是说当用户点击的时候，首先MotionEventACTION_DOWN，onDown就会执行，
         * 如果在按下的瞬间没有松开或者是拖动的时候onShowPress就会执行，如果是按下的时间超过瞬间
         * （这块我也不太清楚瞬间的时间差是多少，一般情况下都会执行onShowPress），拖动了，就不执行onShowPress。
         */
        override fun onShowPress(e: MotionEvent?) {
            Log.i("MyGesture", "onShowPress")
            Toast.makeText(this@FollowViewActivity, "onShowPress", Toast.LENGTH_SHORT).show()
        }

        //用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
        //轻击一下屏幕，立刻抬起来，才会有这个触发
        //从名子也可以看出,一次单独的轻击抬起操作,当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以这个事件 就不再响应
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            Log.i("MyGesture", "onSingleTapUp")
            Toast.makeText(this@FollowViewActivity, "onSingleTapUp", Toast.LENGTH_SHORT).show()
            return true
        }

        override fun onDown(e: MotionEvent?): Boolean {
            Log.i("MyGesture", "onDown")
            Toast.makeText(this@FollowViewActivity, "onDown", Toast.LENGTH_SHORT).show()
            return false
        }

        // 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            Log.i("MyGesture", "onFling")
            Toast.makeText(this@FollowViewActivity, "onFling", Toast.LENGTH_LONG).show()
            return true
        }

        // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            Log.i("MyGesture22", "onScroll:" + (e2!!.x - e1!!.x) + "   " + distanceX)
            Toast.makeText(this@FollowViewActivity, "onScroll", Toast.LENGTH_LONG).show()
            return true
        }

        // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
        override fun onLongPress(e: MotionEvent?) {
            Log.i("MyGesture", "onLongPress")
            Toast.makeText(this@FollowViewActivity, "onLongPress", Toast.LENGTH_LONG).show()
        }

        //双击事件
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            Log.i("MyGesture", "onSingleTapConfirmed");
            Toast.makeText(this@FollowViewActivity, "onSingleTapConfirmed", Toast.LENGTH_LONG).show();
            return true;

        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            Log.i("MyGesture", "onDoubleTap");
            Toast.makeText(this@FollowViewActivity, "onDoubleTap", Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
