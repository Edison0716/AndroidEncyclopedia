package com.htxcsoft.module_photo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils

import com.htxcsoft.module_photo.adapter.ExhibitPhotoAdapter
import com.junlong0716.module.common.base.BaseActivity
import com.junlong0716.module.picture.R
import kotlinx.android.synthetic.main.activity_exhibit_photo.*


/**
 *@author: 巴黎没有摩天轮Li
 *@description: 照片展示界面
 *@date: Created in 下午9:30 2017/12/24
 *@modified by:
 */

@Route(path = "/module_picture/exhibit_photo_activity")
class ExhibitPhotoActivity : BaseActivity<ExhibitPhotoContract.Presenter>() {

    //private lateinit var mPhotoList: ArrayList<View>
    private lateinit var mPhotoUrl: ArrayList<String>
    private lateinit var mPhotoList: ArrayList<Fragment>

    override fun beforeSetLayout() {
        var bundle = intent.extras
        var picUrl = bundle.getString("picUrl")
        mPhotoUrl = ArrayList()
        mPhotoUrl.add(picUrl)
    }

    override fun getLayoutId(): Int = R.layout.activity_exhibit_photo

    @SuppressLint("RestrictedApi")
    override fun initView(savedInstanceState: Bundle?) {
        showSystemUI()
        setSupportActionBar(toolbar)
        toolbar.bringToFront()
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        supportActionBar!!.setShowHideAnimationEnabled(true)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        supportActionBar!!.title = ""

        mPhotoList = ArrayList()
        for (i in 0 until mPhotoUrl.size) {
            mPhotoList.add(ExhibitPhotoFragment.newInstance(mPhotoUrl[i], (i + 1).toString()))
        }

        tv_page_indicator.text = "1/${mPhotoUrl.size}"
        var exhibitPhotoAdapter = ExhibitPhotoAdapter(supportFragmentManager, mPhotoList)
        viewpager.adapter = exhibitPhotoAdapter
        viewpager.offscreenPageLimit = 1
        exhibitPhotoAdapter.notifyDataSetChanged()

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                tv_page_indicator.text = "${position + 1}/${mPhotoUrl.size}"
            }
        })

        hideOrShowToolbar()

    }

    override fun attachPresenter() {
        mPresenter = ExhibitPhotoPresenter()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    fun hideOrShowToolbar() {
        if (supportActionBar!!.isShowing()) {
            supportActionBar!!.hide()
            hideSystemUI()
        } else {
            showSystemUI()
            supportActionBar!!.show()
        }
    }


    protected fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        tv_page_indicator.visibility = View.GONE
        tv_save.visibility = View.GONE
    }

    protected fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        tv_page_indicator.visibility = View.VISIBLE
        tv_save.visibility = View.VISIBLE
    }
}
