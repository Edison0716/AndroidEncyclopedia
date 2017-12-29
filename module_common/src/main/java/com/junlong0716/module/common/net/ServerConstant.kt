package com.junlong0716.module.common.net

import com.junlong0716.module.common.net.model.BasicResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *@author: 巴黎没有摩天轮Li
 *@description: Service Api
 *@date: Created in 上午10:44 2017/12/29
 *@modified by:
 */
object ServerConstant {

    //超时时间
    val DEFAULT_TIMEOUT: Long = 20000

    //主机地址
    private val SERVICE_HOST = "http://gank.io/"

    //请求服务端地址
    val BASE_SERVER_URL: String = SERVICE_HOST + "api/data/"

}