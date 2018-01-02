package com.htxcsoft.module_photo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * @author: 巴黎没有摩天轮Li
 * @description:
 * @date: Created in 下午1:55 2017/12/25
 * @modified by:
 */
class ExhibitPhotoAdapter(fm: FragmentManager, items: List<Fragment>) : FragmentStatePagerAdapter(fm) {
    private var items: List<Fragment> = items

    override fun getItem(position: Int): Fragment = items[position]

    override fun getCount(): Int = items.size

    //    private var context: Context = context
//    private var photoViews: List<View> = items
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
//
//    override fun getCount(): Int {
//        return photoViews.size
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val view = photoViews[position]
//        container.addView(view)
//        return view
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(photoViews[position])
//    }


}
