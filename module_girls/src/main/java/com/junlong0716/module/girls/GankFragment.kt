package com.junlong0716.module.girls

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.junlong0716.module.common.base.BaseFragment
import com.junlong0716.module.common.net.DefaultObserver
import com.junlong0716.module.common.net.RetrofitClient
import com.junlong0716.module.common.net.model.BasicResponse
import com.junlong0716.module.common.rxbus.RxBus
import com.junlong0716.module.common.rxbus.Subscribe
import com.junlong0716.module.common.rxbus.ThreadMode
import com.junlong0716.module.common.utilcode.util.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午1:15 2018/1/1
 *@modified by:
 */
class GankFragment : BaseFragment() {
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var girlsList: ArrayList<MeiZi>
    private lateinit var girlsAdapter: GirlsAdapter

    //EventBus 3.0 回调
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventBus(r: GirlsComingEvent) {
        if (r.from == "GankFragment") {
            refreshLayout.isRefreshing = false
            girlsList.addAll(r.girls)
            girlsAdapter.notifyDataSetChanged()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        girlsList = ArrayList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        RetrofitClient.getRetrofitClient().create(ServerApi::class.java)
                .getMezi()
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<BasicResponse<List<MeiZi>>>(parentFragment!!.activity!!, false) {
                    override fun onSuccess(response: BasicResponse<List<MeiZi>>) {
//                        girlsList.addAll(response.results as ArrayList<MeiZi>)
//                        refreshLayout.isRefreshing = false
//                        girlsAdapter.notifyDataSetChanged()

                        GirlService.start(this@GankFragment.context!!, "GankFragment", response.results as ArrayList<MeiZi>)
                    }
                })

//        RetrofitClient.getRetrofitClient().create(ServerApi::class.java)
//                .getMeziJD(0)
//                .compose(this.bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Observer<RequestBody>{
//                    override fun onError(e: Throwable) {
//                      Log.e("error",e.message)
//
//                    }
//
//                    override fun onComplete() {
//
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//
//                    }
//
//                    override fun onNext(t: RequestBody) {
//                        ToastUtils.showShort(t.toString())
//                    }
//
//                })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        RxBus.getDefault().register(this)
        var view = inflater.inflate(R.layout.fragment_girls_list, container, false)
        refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.srl_refresh)
        refreshLayout.post({
            refreshLayout.isRefreshing = true;
        })

        var rvGirlsList = view.findViewById<RecyclerView>(R.id.rv_girls_list)
        rvGirlsList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //rvGirlsList.layoutManager = LinearLayoutManager(activity)
        girlsAdapter = GirlsAdapter(girlsList, context!!)
        rvGirlsList.adapter = girlsAdapter

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this)
        }
    }
}