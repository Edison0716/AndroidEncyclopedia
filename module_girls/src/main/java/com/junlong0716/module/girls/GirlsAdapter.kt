package com.junlong0716.module.girls

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.htxcsoft.corelibrary.glide.GlideApp
import com.junlong0716.module.common.widget.RatioImageView

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午1:50 2018/1/1
 *@modified by:
 */
//class GirlsAdapter(data:ArrayList<MeiZi>,layoutResId:Int) :BaseQuickAdapter<MeiZi,BaseViewHolder>(layoutResId,data){
//
//
//    override fun convert(helper: BaseViewHolder?, item: MeiZi?) {
//        ToastUtils.showShort(item!!.getUrl())
//        Glide.with(mContext).load(item!!.getUrl()).into(helper!!.getView(R.id.iv_girl))
//    }
//
//}

class GirlsAdapter(girls: ArrayList<MeiZi>, context: Context) : RecyclerView.Adapter<GirlsAdapter.GirlsViewHolder>() {
    private var girls: ArrayList<MeiZi> = girls
    private var context: Context = context
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun getItemCount(): Int = if (girls == null) 0 else girls.size

    override fun onBindViewHolder(holder: GirlsViewHolder?, position: Int) {
        var girl = girls[position]

//        var bitmip = GlideApp.with(context).asBitmap().load(girl.getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL)
//                .submit().get()
//
//        if (bitmip.height != 0) {
//            holder!!.ivGirl.setOriginalSize(bitmip.width, bitmip.height)
//        } else {
//            holder!!.ivGirl.setOriginalSize(236, 354)
//        }

        GlideApp.with(context).load(girl.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_glide_holder)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(holder!!.ivGirl)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GirlsViewHolder {
        var view = inflater.inflate(R.layout.item_girl_layout, parent, false)
        var holder = GirlsViewHolder(view)
        return holder
    }


    inner class GirlsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivGirl: RatioImageView = view.findViewById(R.id.iv_girl)
    }


    override fun getItemId(position: Int): Long = girls[position].hashCode().toLong()
}