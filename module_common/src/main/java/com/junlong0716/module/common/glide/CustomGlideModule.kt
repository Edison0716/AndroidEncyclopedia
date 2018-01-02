package com.htxcsoft.corelibrary.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.junlong0716.module.common.glide.ProgressInterceptor
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 *@author: 巴黎没有摩天轮Li
 *@description: 自定义Glide
 *@date: Created in 下午9:28 2017/12/22
 *@modified by:
 */
@GlideModule
class CustomGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)
        builder!!.setDiskCache(InternalCacheDiskCacheFactory(context, GlideCatchConfig.GLIDE_CARCH_DIR, GlideCatchConfig.GLIDE_CATCH_SIZE))
    }

    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        super.registerComponents(context, glide, registry)
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(ProgressInterceptor())
        val okHttpClient = builder.build()
        registry!!.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))
    }
}