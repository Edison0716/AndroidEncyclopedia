package com.junlong0716.module.girls.api

import com.junlong0716.module.common.net.model.BasicResponse
import com.junlong0716.module.girls.model.MeiZi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午12:39 2017/12/29
 *@modified by:
 */
interface ServerApi {

    @Headers("Cache-Control: public, max-age=100") //设置缓存 缓存时间为100s
    @GET("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/{page}")
    fun getMezi(@Path("page") page: String): Observable<BasicResponse<List<MeiZi>>>

}