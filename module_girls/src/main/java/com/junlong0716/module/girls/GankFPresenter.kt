package com.junlong0716.module.girls

import com.blankj.utilcode.util.ToastUtils
import com.junlong0716.module.common.base.BasePresenter
import com.junlong0716.module.common.net.DefaultObserver
import com.junlong0716.module.common.net.RetrofitClient
import com.junlong0716.module.common.net.model.BasicResponse
import com.junlong0716.module.girls.api.ServerApi
import com.junlong0716.module.girls.model.MeiZi
import com.junlong0716.module.girls.service.GirlService
import com.trello.rxlifecycle2.components.support.RxFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 上午10:59 2018/1/2
 *@modified by:
 */
class GankFPresenter : BasePresenter<GankFContract.View>(), GankFContract.Presenter {
    override fun requestGankMeiZi(fragment: RxFragment, page: String) {
        RetrofitClient.getRetrofitClient().create(ServerApi::class.java)
                .getMezi(page)
                .compose(fragment.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<BasicResponse<List<MeiZi>>>(fragment!!.activity!!, false) {
                    override fun onSuccess(response: BasicResponse<List<MeiZi>>) {
                        GirlService.start(fragment.context!!, "GankFragment", response.results as ArrayList<MeiZi>)
                    }
                })
    }

    override fun onDestroy() {
        ToastUtils.showShort("presenter onDestroy")
    }
}