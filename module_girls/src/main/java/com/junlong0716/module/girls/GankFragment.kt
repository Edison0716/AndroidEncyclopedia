package com.junlong0716.module.girls

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.junlong0716.module.common.base.BaseFragment
import com.junlong0716.module.common.net.DefaultObserver
import com.junlong0716.module.common.net.RetrofitClient
import com.junlong0716.module.common.net.model.BasicResponse
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
    private lateinit var girlsAdapter:GirlsAdapter

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
                        girlsList.addAll(response.results as ArrayList<MeiZi>)
                        refreshLayout.isRefreshing = false
                        girlsAdapter.notifyDataSetChanged()
                    }
                })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_girls_list, container, false)
        refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.srl_refresh)
        refreshLayout.post({
            refreshLayout.isRefreshing = true;
        })

        var rvGirlsList = view.findViewById<RecyclerView>(R.id.rv_girls_list)
        rvGirlsList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //rvGirlsList.layoutManager = LinearLayoutManager(activity)
        girlsAdapter = GirlsAdapter(girlsList,R.layout.item_girl_layout)
        rvGirlsList.adapter = girlsAdapter

        return view
    }
}