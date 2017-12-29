package com.junlong0716.module.common.net

import android.app.Activity
import com.junlong0716.module.common.net.model.BasicResponse
import io.reactivex.Observer
import com.afollestad.materialdialogs.MaterialDialog
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.text.TextUtils
import android.widget.Toast
import com.google.gson.JsonParseException
import com.junlong0716.module.common.R
import com.junlong0716.module.common.utilcode.util.LogUtils
import com.junlong0716.module.common.utilcode.util.SnackbarUtils
import com.junlong0716.module.common.utilcode.util.ToastUtils
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.adapter.rxjava2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException


/**
 * @author: 巴黎没有摩天轮Li
 * @description: 处理返回体
 * @date: Created in 下午12:55 2017/12/29
 * @modified by:
 */
abstract class DefaultObserver<T : BasicResponse<*>> : Observer<T> {
    private var activity: Activity
    private lateinit var dialog: MaterialDialog

    constructor(activity: Activity) {
        this.activity = activity
        /*加载进度条操作*/
        dialog = MaterialDialog.Builder(activity)
                .content(R.string.please_wait)
                .progress(true, 0)
                .canceledOnTouchOutside(true)
                .build()
        dialog.show()
    }


    constructor(activity: Activity, isShowLoading: Boolean) {
        this.activity = activity
        /*加载进度条操作*/
        dialog = MaterialDialog.Builder(activity)
                .content(R.string.please_wait)
                .progress(true, 0)
                .canceledOnTouchOutside(true)
                .build()
        if (isShowLoading) {
            dialog.show()
        }
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {
        dialog.dismiss()

        if (!t.isError) {
            onSuccess(t)
        } else {
            onFail(t)
        }

    }

    /**
     *网络请求失败
     *
     * @param response 服务器返回的数据
     */
    private fun onFail(response: T) {
        var message = response.message
        if (TextUtils.isEmpty(message)) {
            ToastUtils.showShort(R.string.response_return_error)
        } else {
            ToastUtils.showShort(message)
        }
    }


    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract fun onSuccess(response: T)


    override fun onError(e: Throwable) {
        LogUtils.e("Retrofit", e.message)
        dialog.dismiss()
        if (e is HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK)
        } else if (e is ConnectException || e is UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR)
        } else if (e is InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT)
        } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR)
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR)
        }
    }


    override fun onComplete() {

    }


    /**
     * 请求异常
     *
     * @param reason
     */
    fun onException(reason: ExceptionReason) {
        when (reason) {
            DefaultObserver.ExceptionReason.CONNECT_ERROR -> ToastUtils.showShort(R.string.connect_error, Toast.LENGTH_SHORT)

            DefaultObserver.ExceptionReason.CONNECT_TIMEOUT -> ToastUtils.showShort(R.string.connect_timeout, Toast.LENGTH_SHORT)

            DefaultObserver.ExceptionReason.BAD_NETWORK -> ToastUtils.showShort(R.string.bad_network, Toast.LENGTH_SHORT)

            DefaultObserver.ExceptionReason.PARSE_ERROR -> ToastUtils.showShort(R.string.parse_error, Toast.LENGTH_SHORT)

            DefaultObserver.ExceptionReason.UNKNOWN_ERROR -> ToastUtils.showShort(R.string.unknown_error, Toast.LENGTH_SHORT)

            else -> ToastUtils.showShort(R.string.unknown_error)
        }
    }

    /**
     * 请求网络失败原因
     */
    enum class ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR
    }
}
