package com.junlong0716.module.girls.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.htxcsoft.corelibrary.glide.GlideApp
import com.junlong0716.module.common.constant.Constant
import com.junlong0716.module.common.widget.RatioImageView
import com.junlong0716.module.girls.R
import com.junlong0716.module.girls.model.MeiZi

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午1:50 2018/1/1
 *@modified by:
 */
class GirlsAdapter(girls: ArrayList<MeiZi>, context: Context) : RecyclerView.Adapter<GirlsAdapter.GirlsViewHolder>() {
    private var girls: ArrayList<MeiZi> = girls
    private var context: Context = context
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun getItemCount(): Int = if (girls == null) 0 else girls.size

    override fun onBindViewHolder(holder: GirlsViewHolder?, position: Int) {
        var girl = girls[position]

        if (girl.getHeight() != 0) {
            try {
                holder!!.ivGirl.setOriginalSize(girl.getWidth()!!, girl.getHeight()!!)
            } catch (e: Exception) {
                holder!!.ivGirl.setOriginalSize(236, 354)
            }

        } else {
            holder!!.ivGirl.setOriginalSize(236, 354)
        }

        GlideApp.with(context)
                .load(girl.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_glide_holder)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(holder!!.ivGirl)

        holder.ivGirl.setOnClickListener { p0 -> startPictureActivity(p0!!, position) }

    }

    private fun startPictureActivity(transitView: View, position: Int) {
        var options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as AppCompatActivity, transitView, Constant.TRANSIT_PIC)
        var bundle = Bundle()
        var girlsList = ArrayList<String>()
        girlsList.add(girls[position].getUrl()!!)
        bundle.putStringArrayList("picUrl", girlsList)
        ARouter.getInstance().build("/module_picture/exhibit_photo_activity").with(bundle).withOptionsCompat(options).navigation(context as AppCompatActivity)
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

    fun addData(position: Int, data: List<MeiZi>) {
        this.girls.addAll(position, data)
        this.notifyItemRangeInserted(position, data.size)
    }

    fun getData(): List<MeiZi> {
        return girls
    }
}