package com.junlong0716.module.girls

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.junlong0716.module.common.utilcode.util.ToastUtils

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午1:50 2018/1/1
 *@modified by:
 */
class GirlsAdapter(data:ArrayList<MeiZi>,layoutResId:Int) :BaseQuickAdapter<MeiZi,BaseViewHolder>(layoutResId,data){


    override fun convert(helper: BaseViewHolder?, item: MeiZi?) {
        ToastUtils.showShort(item!!.getUrl())
        Glide.with(mContext).load(item!!.getUrl()).into(helper!!.getView(R.id.iv_girl))
    }

}