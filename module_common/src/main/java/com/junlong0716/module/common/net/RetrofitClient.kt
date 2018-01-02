package com.junlong0716.module.common.net

import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.Utils
import com.google.gson.GsonBuilder
import com.junlong0716.module.common.net.ServerConstant.BASE_SERVER_URL
import com.junlong0716.module.common.net.ServerConstant.DEFAULT_TIMEOUT
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *@author: 巴黎没有摩天轮Li
 *@description: Retrofit
 *@date: Created in 上午10:07 2017/12/29
 *@modified by:
 */
class RetrofitClient {
    private var retrofit: Retrofit

    companion object {
        private var retrofitClient = RetrofitClient()

        fun getRetrofitClient(): Retrofit {
            return retrofitClient.retrofit
        }
    }

    constructor() {
        var interceptor = HttpLoggingInterceptor(/*HttpLoggingInterceptor.Logger { message ->
            try {
                val text = URLDecoder.decode(message, "utf-8")
                LogUtils.e("OKHttp-----", text)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
                LogUtils.e("OKHttp-----", message)
            }
        }*/)

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        var cacheFile = File(Utils.getApp().cacheDir, "cache")
        val cache = Cache(cacheFile, (1024 * 1024 * 100).toLong()) //100Mb

        var okHttpClient = OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(HttpCacheInterceptor())
                .cache(cache)
                .build()

        var gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()

        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }


    inner class HttpCacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            var request = chain!!.request()
            if (!NetworkUtils.isConnected()) {  //没网强制从缓存读取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            val originalResponse = chain.proceed(request)
            return if (NetworkUtils.isConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                val cacheControl = request.cacheControl().toString()
                originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build()
            } else {
                originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build()
            }
        }
    }
}