package com.htxcsoft.module.android.knowledge

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.htxcsoft.module.android.knowledge.adapter.KnowledgeAdapter
import com.htxcsoft.module.android.knowledge.model.KnowledgeBean
import com.htxcsoft.module.android.knowledge.module.android.view.ActivityDispatchEventActivity
import com.htxcsoft.module.android.knowledge.module.android.view.DispatchEventActivity
import com.htxcsoft.module.android.knowledge.module.android.view.FollowViewActivity
import com.junlong0716.module.common.base.BaseFragment
import com.junlong0716.module.common.event.DrawerEvent
import com.junlong0716.module.common.rxbus.RxBus

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午3:17 2018/2/10
 *@modified by:
 */
@Route(path = "/module_android_knowledge/AndroidKnowledgeFragment")
class AndroidKnowledgeFragment : BaseFragment<AndroidKnowledgeFPresenter>(), AndroidKnowledgeFContract.View, KnowledgeAdapter.OnItemClickListener {
    private lateinit var rvList: RecyclerView
    private lateinit var knowledgeList: ArrayList<KnowledgeBean>

    override
    fun getLayoutId(): Int = R.layout.fragment_android_knowledge

    override fun initViews(mRootView: View?) {
        knowledgeList = ArrayList()
        knowledgeList.add(KnowledgeBean("View事件"))
        knowledgeList.add(KnowledgeBean("View事件分发机制教程"))
        knowledgeList.add(KnowledgeBean("View事件分发机制实例"))

        var toolBar = mRootView!!.findViewById<Toolbar>(R.id.toolbar)
        toolBar.title = getString(R.string.menu_android_knowledge)
        RxBus.getDefault().post(DrawerEvent(toolBar))
        rvList = mRootView.findViewById(R.id.rv_list)
        rvList.layoutManager = LinearLayoutManager(activity)
        rvList.adapter = KnowledgeAdapter(knowledgeList, this)
    }

    override fun lazyFetchData() {

    }

    override fun attachPresenter() {
        mPresenter = AndroidKnowledgeFPresenter()
        mPresenter!!.attachView(this)
    }

    override fun onItemClickListener(position: Int) {
        when (position) {
            0 -> startActivity(Intent(activity, FollowViewActivity::class.java))
            1 -> startActivity(Intent(activity, DispatchEventActivity::class.java))
            2 -> startActivity(Intent(activity, ActivityDispatchEventActivity::class.java))
        }
    }

}