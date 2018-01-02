package com.junlong0716.module.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.alibaba.android.arouter.launcher.ARouter
import com.junlong0716.module.main.utils.RxDrawer
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_splash.*


class MainActivity : RxAppCompatActivity() {
    private lateinit var girlsFragment: Fragment
    private var currentFragmentTag: String? = null
    private val FRAGMENT_TAG_GIRLS = "girls"
    private val CURRENT_INDEX = "currentIndex"
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView(savedInstanceState)
        initFragment(savedInstanceState)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            switchContent(FRAGMENT_TAG_GIRLS)
        } else {
            currentFragmentTag = savedInstanceState.getString(CURRENT_INDEX)
            switchContent(currentFragmentTag!!)
        }
    }

    private fun switchContent(name: String) {
        if (currentFragmentTag != null && currentFragmentTag == name) {
            return
        }
        val ft = fragmentManager.beginTransaction()
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        val currentFragment = fragmentManager.findFragmentByTag(currentFragmentTag)

        if (currentFragment != null) {
            ft.hide(currentFragment)
        }
        var foundFragment: Fragment? = fragmentManager.findFragmentByTag(name)

        if (foundFragment == null) {
            when (name) {
                FRAGMENT_TAG_GIRLS -> {
                    foundFragment = ARouter.getInstance().build("/module_girls/GirlsFragment").navigation() as Fragment
                }
            }
        }
        when {
            foundFragment == null -> {
            }
            foundFragment.isAdded -> ft.show(foundFragment)
            else -> ft.add(R.id.contentLayout, foundFragment, name)
        }
        ft.commit()
        currentFragmentTag = name
        invalidateOptionsMenu()
    }

    private fun initView(savedInstanceState: Bundle?) {
        fragmentManager = supportFragmentManager
        navigation_view.inflateHeaderView(R.layout.drawer_header)
        navigation_view.setCheckedItem(R.id.menu_girls)
        navigation_view.setNavigationItemSelectedListener(NavigationItemSelected())
    }

    inner class NavigationItemSelected : NavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            RxDrawer.closeDrawer(drawer_layout)!!.observeOn(AndroidSchedulers.mainThread()).subscribe {
                when (item.itemId) {
                    R.id.menu_girls -> {
                        item.isChecked = true
                        ARouter.getInstance().build("/module_girls/GirlsActivity").navigation()
                    }
                }
            }
            return false
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putString(CURRENT_INDEX, currentFragmentTag)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) run { drawer_layout.closeDrawer(GravityCompat.START) } else {
            super.onBackPressed()
        }
    }
}

