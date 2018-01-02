package com.junlong0716.module.girls

import com.junlong0716.module.common.base.IPresenter
import com.junlong0716.module.common.utilcode.util.ToastUtils

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 上午10:59 2018/1/2
 *@modified by:
 */
class GankFPresenter : IPresenter{
    override fun onDestroy() {
        ToastUtils.showShort("presenter onDestroy")
    }
}