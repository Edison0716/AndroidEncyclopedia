package com.htxcsoft.module_photo

import android.util.Log
import com.junlong0716.module.common.base.BasePresenter

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午5:00 2017/12/26
 *@modified by:
 */
class ExhibitPhotoPresenter : BasePresenter<ExhibitPhotoContract.View>(),ExhibitPhotoContract.Presenter{
    override fun onDestroy() {
        Log.e("MainPresenter","destroy")
    }

}