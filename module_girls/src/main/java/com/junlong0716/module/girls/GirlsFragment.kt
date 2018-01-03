package com.junlong0716.module.girls

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.junlong0716.module.common.event.DrawerEvent
import com.junlong0716.module.common.rxbus.RxBus
import java.util.ArrayList

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午4:09 2017/12/29
 *@modified by:
 */

@Route(path = "/module_girls/GirlsFragment")
class GirlsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_girls, container, false)

        var pager = view.findViewById<ViewPager>(R.id.view_pager)

        var tab = view.findViewById<TabLayout>(R.id.tabs)

        var toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.title = "福利"

        RxBus.getDefault().post(DrawerEvent(toolbar))

        var mPagerAdapter = ViewPagerAdapter(childFragmentManager)

        mPagerAdapter.addFrag(GankFragment(), "干货")

        pager.adapter = mPagerAdapter

        pager.offscreenPageLimit = pager.getAdapter()!!.getCount()

        tab.setupWithViewPager(pager)

        tab.tabMode = TabLayout.MODE_SCROLLABLE

        return view
    }


    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val mFragmentList = ArrayList<Fragment>()

        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment = mFragmentList.get(position)

        override fun getCount(): Int = mFragmentList.size

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}