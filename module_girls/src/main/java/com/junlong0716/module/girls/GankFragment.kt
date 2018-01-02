package com.junlong0716.module.girls

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.junlong0716.module.common.base.BaseFragment
import com.junlong0716.module.common.net.DefaultObserver
import com.junlong0716.module.common.net.RetrofitClient
import com.junlong0716.module.common.net.model.BasicResponse
import com.junlong0716.module.common.rxbus.Subscribe
import com.junlong0716.module.common.rxbus.ThreadMode
import com.junlong0716.module.girls.adapter.GirlsAdapter
import com.junlong0716.module.girls.api.ServerApi
import com.junlong0716.module.girls.event.GirlsComingEvent
import com.junlong0716.module.girls.model.MeiZi
import com.junlong0716.module.girls.service.GirlService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午1:15 2018/1/1
 *@modified by:
 */
class GankFragment : BaseFragment<GankFPresenter>() {


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

    override fun getLayoutId(): Int = R.layout.fragment_girls_list

    override fun attachPresenter() {
        mPresenter = GankFPresenter()
    }

    override fun initViews(mRootView: View?) {

        refreshLayout = mRootView!!.findViewById<SwipeRefreshLayout>(R.id.srl_refresh)
        refreshLayout.post({
            refreshLayout.isRefreshing = true;
        })

        var rvGirlsList = mRootView.findViewById<RecyclerView>(R.id.rv_girls_list)
        rvGirlsList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        girlsAdapter = GirlsAdapter(girlsList, context!!)
        rvGirlsList.adapter = girlsAdapter
    }

    override fun lazyFetchData() {
        RetrofitClient.getRetrofitClient().create(ServerApi::class.java)
                .getMezi("1")
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<BasicResponse<List<MeiZi>>>(parentFragment!!.activity!!, false) {
                    override fun onSuccess(response: BasicResponse<List<MeiZi>>) {
                        GirlService.start(this@GankFragment.context!!, "GankFragment", response.results as ArrayList<MeiZi>)
                    }
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        girlsList = ArrayList()
    }

    override fun onDestroy() {
        GirlService.stop(this@GankFragment.context!!)
        super.onDestroy()
    }

}