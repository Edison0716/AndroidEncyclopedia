package com.junlong0716.module.girls.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.htxcsoft.corelibrary.glide.GlideApp
import com.junlong0716.module.common.rxbus.RxBus
import com.junlong0716.module.girls.event.GirlsComingEvent
import com.junlong0716.module.girls.model.MeiZi
import java.io.Serializable

/**
 *@author: 巴黎没有摩天轮Li
 *@description: 获取图片的宽高
 *@date: Created in 上午8:12 2018/1/2
 *@modified by:
 */

class GirlService : IntentService("GirlService") {
    companion object {
        private val KEY_EXTRA_GIRL_FROM = "from"
        private val KEY_EXTRA_GIRL_LIST = "data"
        fun start(context: Context, from: String, girlList: ArrayList<MeiZi>) {
            val intent = Intent(context, GirlService::class.java)
            intent.putExtra(KEY_EXTRA_GIRL_FROM, from)
            intent.putExtra(KEY_EXTRA_GIRL_LIST, girlList as Serializable)
            context.startService(intent)
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, GirlService::class.java))
        }
    }


    override fun onHandleIntent(p0: Intent?) {
        var from = p0!!.getStringExtra(KEY_EXTRA_GIRL_FROM)
        var girls = p0!!.getSerializableExtra(KEY_EXTRA_GIRL_LIST) as ArrayList<MeiZi>

        for (i in 0 until girls.size) {
            var bitmap: Bitmap? = null
            //防止OOM
            try {
                bitmap = GlideApp.with(this).asBitmap()
                        .load(girls[i].getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .submit()
                        .get()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (bitmap != null) {
                girls[i].setHeight(bitmap.height)
                girls[i].setWidth(bitmap.width)
            }
        }

        RxBus.getDefault().post(GirlsComingEvent(from, girls))
    }
}