package com.htxcsoft.module_photo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route

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
        mPhotoUrl = ArrayList()
        mPhotoUrl.add("http://www.4k123.com/data/attachment/forum/201704/11/180811iwtvfu9kvulgratt.jpg")
        mPhotoUrl.add("http://www.4k123.com/data/attachment/forum/201704/11/180811fme9905l11eil5nk.jpg")
        mPhotoUrl.add("http://www.4k123.com/data/attachment/forum/201704/11/180812q7zltokl77ozkzno.jpg")
        mPhotoUrl.add("http://www.4k123.com/data/attachment/forum/201704/11/180812u4zxe4n4tdk4kcni.jpg")
        mPhotoUrl.add("http://tupian.enterdesk.com/2015/mxy/6/14/12/3.jpg")
    }

    override fun getLayoutId(): Int = R.layout.activity_exhibit_photo

    override fun initView(savedInstanceState: Bundle?) {
        iv_back.bringToFront()
        iv_back.setOnClickListener({
            onBackPressed()
        })
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

    }

    override fun attachPresenter() {
        mPresenter = ExhibitPhotoPresenter()
    }

}
