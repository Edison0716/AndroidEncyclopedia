package com.junlong0716.module.main.utils

import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.View
import io.reactivex.Observable
import io.reactivex.ObservableSource

/**
 * @author: 巴黎没有摩天轮Li
 * @description:
 * @date: Created in 下午3:11 2017/12/29
 * @modified by:
 */
object RxDrawer {
    private val OFFSET_THRESHOLD = 0.03f

    fun closeDrawer(drawer: DrawerLayout): Observable<String>? {
        return Observable.create { subscriber->
            drawer.closeDrawer(GravityCompat.START)
            val listener = object : DrawerLayout.SimpleDrawerListener() {
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    if (slideOffset < OFFSET_THRESHOLD) {
                        subscriber.onNext("")
                        subscriber.onComplete()
                    }
                }
            }
            drawer.addDrawerListener(listener)
            subscriber.setCancellable {
                drawer.removeDrawerListener(listener)
            }
        }

    }
}
