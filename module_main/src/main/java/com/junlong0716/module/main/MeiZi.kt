package com.junlong0716.module.main

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 上午11:10 2017/12/29
 *@modified by:
 */
class MeiZi{
    /**
     * _id : 59cd9b53421aa9727fdb25eb
     * createdAt : 2017-09-29T09:01:07.894Z
     * desc : 9-29
     * publishedAt : 2017-09-29T11:21:16.116Z
     * source : chrome
     * type : 福利
     * url : https://ws1.sinaimg.cn/large/610dc034ly1fk05lf9f4cj20u011h423.jpg
     * used : true
     * who : daimajia
     */
    private var _id: String? = null
    private var createdAt: String? = null
    private var desc: String? = null
    private var publishedAt: String? = null
    private var source: String? = null
    private var type: String? = null
    private var url: String? = null
    private var used: Boolean = false
    private var who: String? = null

    fun get_id(): String? {
        return _id
    }

    fun set_id(_id: String) {
        this._id = _id
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String) {
        this.createdAt = createdAt
    }

    fun getDesc(): String? {
        return desc
    }

    fun setDesc(desc: String) {
        this.desc = desc
    }

    fun getPublishedAt(): String? {
        return publishedAt
    }

    fun setPublishedAt(publishedAt: String) {
        this.publishedAt = publishedAt
    }

    fun getSource(): String? {
        return source
    }

    fun setSource(source: String) {
        this.source = source
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun isUsed(): Boolean {
        return used
    }

    fun setUsed(used: Boolean) {
        this.used = used
    }

    fun getWho(): String? {
        return who
    }

    fun setWho(who: String) {
        this.who = who
    }
}
