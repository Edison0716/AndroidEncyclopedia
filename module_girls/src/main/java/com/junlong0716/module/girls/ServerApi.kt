package com.junlong0716.module.girls

import com.junlong0716.module.common.net.model.BasicResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午12:39 2017/12/29
 *@modified by:
 */
interface ServerApi {

    @Headers("Cache-Control: public, max-age=100") //设置缓存 缓存时间为100s
    @GET("福利/10/1")
    abstract fun getMezi(): Observable<BasicResponse<List<MeiZi>>>

}