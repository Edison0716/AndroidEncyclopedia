package com.junlong0716.module.common.base


/**
 *@author: 巴黎没有摩天轮Li
 *@description: presenter层 基类
 *@date: Created in 上午10:34 2017/11/10
 *@modified by:
 */
abstract class BasePresenter<V : IView> {
    /**
     * bind view
     */
    private var mvpView: V? = null

    /**
     * bind view，use the function when activity on create
     */
    fun attachView(mvpView: V) {
        this.mvpView = mvpView
    }

    /**
     * disconnect view，use the function when activity on destroy
     */
    fun detachView() {
        this.mvpView = null
    }

    /**
     * judge the view is connect
     */
    fun isViewAttached(): Boolean = mvpView != null

    /**
     * get the bind view
     */
    fun getView(): V? = mvpView

}