package com.junlong0716.module.weather

import android.support.v7.widget.Toolbar
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.junlong0716.module.common.base.BaseFragment
import com.junlong0716.module.common.event.DrawerEvent
import com.junlong0716.module.common.rxbus.RxBus

/**
 *@author: 巴黎没有摩天轮Li
 *@description: 天气首页
 *@date: Created in 下午12:40 2018/1/3
 *@modified by:
 */
@Route(path = "/module_weather/WeatherFragment")
class WeatherFragment : BaseFragment<WeatherFPresenter>(), WeatherFContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_weather

    override fun initViews(mRootView: View?) {
        var toolBar = mRootView!!.findViewById<Toolbar>(R.id.toolbar)
        RxBus.getDefault().post(DrawerEvent(toolBar))


    }

    override fun lazyFetchData() {

    }

    override fun attachPresenter() {
        mPresenter = WeatherFPresenter()
        mPresenter!!.attachView(this)
    }
}