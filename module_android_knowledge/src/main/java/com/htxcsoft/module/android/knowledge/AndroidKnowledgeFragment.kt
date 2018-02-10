package com.htxcsoft.module.android.knowledge

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.junlong0716.module.common.base.BaseFragment

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午3:17 2018/2/10
 *@modified by:
 */
@Route(path = "/module_android_knowledge/AndroidKnowledgeFragment")
class AndroidKnowledgeFragment : BaseFragment<AndroidKnowledgeFPresenter>(), AndroidKnowledgeFContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_android_knowledge

    override fun initViews(mRootView: View?) {

    }

    override fun lazyFetchData() {

    }

    override fun attachPresenter() {
        mPresenter = AndroidKnowledgeFPresenter()
        mPresenter!!.attachView(this)
    }
}