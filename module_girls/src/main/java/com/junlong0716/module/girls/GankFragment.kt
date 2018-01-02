package com.junlong0716.module.girls

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.junlong0716.module.common.base.BaseFragment
import com.junlong0716.module.common.rxbus.Subscribe
import com.junlong0716.module.common.rxbus.ThreadMode
import com.junlong0716.module.girls.adapter.GirlsAdapter
import com.junlong0716.module.girls.event.GirlsComingEvent
import com.junlong0716.module.girls.model.MeiZi
import com.junlong0716.module.girls.service.GirlService

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午1:15 2018/1/1
 *@modified by:
 */
class GankFragment : BaseFragment<GankFPresenter>(), GankFContract.View, SwipeRefreshLayout.OnRefreshListener {
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var girlsList: ArrayList<MeiZi>
    private lateinit var girlsAdapter: GirlsAdapter
    private var isLoadingMore = false
    private var currentPage: Int = 1

    //EventBus 3.0 回调
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventBus(r: GirlsComingEvent) {
        if (r.from == "GankFragment") {
            if (isLoadingMore) {
                currentPage += 1
                girlsAdapter.addData(girlsAdapter.getData().size, r.girls)
                isLoadingMore = false
            } else {
                refreshLayout.isRefreshing = false
                girlsList.addAll(r.girls)
                girlsAdapter.notifyDataSetChanged()
                currentPage = 2
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_girls_list

    override fun attachPresenter() {
        mPresenter = GankFPresenter()
    }

    override fun initViews(mRootView: View?) {
        refreshLayout = mRootView!!.findViewById<SwipeRefreshLayout>(R.id.srl_refresh)
        refreshLayout.setOnRefreshListener(this)
        refreshLayout.post({
            refreshLayout.isRefreshing = true
        })
        var rvGirlsList = mRootView.findViewById<RecyclerView>(R.id.rv_girls_list)
        rvGirlsList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        girlsAdapter = GirlsAdapter(girlsList, context!!)
        rvGirlsList.adapter = girlsAdapter

        rvGirlsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView!!.canScrollVertically(1) && !isLoadingMore) {
                    isLoadingMore = true
                    mPresenter!!.requestGankMeiZi(this@GankFragment, currentPage.toString())
                }
            }
        })

        girlsAdapter
    }

    override fun lazyFetchData() {
        currentPage = 1
        isLoadingMore = false
        girlsList.clear()
        mPresenter!!.requestGankMeiZi(this, currentPage.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        girlsList = ArrayList()
    }

    override fun onDestroy() {
        GirlService.stop(this@GankFragment.context!!)
        super.onDestroy()
    }

    override fun onRefresh() {
        lazyFetchData()
    }

}