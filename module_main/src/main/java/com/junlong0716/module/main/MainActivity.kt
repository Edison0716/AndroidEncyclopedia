package com.junlong0716.module.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.junlong0716.module.common.event.DrawerEvent
import com.junlong0716.module.common.rxbus.RxBus
import com.junlong0716.module.common.rxbus.Subscribe
import com.junlong0716.module.common.rxbus.ThreadMode
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

    //EventBus 3.0 回调
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventBus(r: DrawerEvent) {
        initDrawer(r.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (!RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().register(this)
        }
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


    fun initDrawer(toolbar: Toolbar?) {
        if (toolbar != null) {
           var toggle = object :ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.open, R.string.close){
               override fun onDrawerOpened(drawerView: View) {
                   super.onDrawerOpened(drawerView)
               }

               override fun onDrawerClosed(drawerView: View) {
                   super.onDrawerClosed(drawerView)
               }
           }
            toggle.syncState()
            drawer_layout.addDrawerListener(toggle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this)
        }
    }
}

