package com.junlong0716.module.weather

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.junlong0716.module.common.base.BaseFragment

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午12:40 2018/1/3
 *@modified by:
 */
@Route(path = "/module_weather/WeatherFragment")
class WeatherFragment : BaseFragment<WeatherFPresenter>(), WeatherFContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_weather

    override fun initViews(mRootView: View?) {

    }

    override fun lazyFetchData() {

    }

    override fun attachPresenter() {
        mPresenter = WeatherFPresenter()
    }
}