package com.junlong0716.module.common.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import com.junlong0716.module.common.rxbus.RxBus
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 *@author: 巴黎没有摩天轮Li
 *@description: Activity 基类
 *@date: Created in 下午12:40 2017/12/29
 *@modified by:
 */
abstract class BaseActivity<P : IPresenter> : RxAppCompatActivity() {
    var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().register(this)
        }
        beforeSetLayout()
        setContentView(getLayoutId())
        attachPresenter()
        initView(savedInstanceState)
    }

    abstract fun beforeSetLayout()

    abstract fun attachPresenter()

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        this.mPresenter!!.onDestroy()
        this.mPresenter = null
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this)
        }
    }
}