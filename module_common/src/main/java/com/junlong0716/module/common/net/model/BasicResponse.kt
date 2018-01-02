package com.junlong0716.module.common.net.model

/**
 * @author: 巴黎没有摩天轮Li
 * @description: 基类 数据返回基本类型
 * @date: Created in 上午11:07 2017/12/29
 * @modified by:
 */
class BasicResponse<T> {

    var code: Int = 0
    var message: String? = null
    var results: T? = null
    var isError: Boolean = false
}
