package com.junlong0716.module.main

import android.os.Bundle
import com.junlong0716.module.common.base.BaseActivity
import com.junlong0716.module.common.net.DefaultObserver
import com.junlong0716.module.common.net.RetrofitClient
import com.junlong0716.module.common.net.model.BasicResponse
import com.junlong0716.module.common.utilcode.util.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        RetrofitClient.getRetrofitClient().create(ServerApi::class.java)
                .getMezi()
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :DefaultObserver<BasicResponse<List<MeiZi>>>(this){
                    override fun onSuccess(response: BasicResponse<List<MeiZi>>) {
                        ToastUtils.showShort(response.results.size.toString())
                    }
                })
    }
}
