package com.junlong0716.module.girls

import com.junlong0716.module.common.base.IPresenter
import com.junlong0716.module.common.base.IView
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午12:49 2018/1/2
 *@modified by:
 */
interface GankFContract {
    interface View : IView {

    }

    interface Presenter : IPresenter {
        fun requestGankMeiZi(fragment: RxFragment, page: String)
    }
}