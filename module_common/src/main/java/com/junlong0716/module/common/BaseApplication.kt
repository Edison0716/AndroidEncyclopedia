package com.junlong0716.module.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.Utils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午9:46 2017/12/22
 *@modified by:
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
        initARouter()

    }

    private fun initARouter() {

        if (AppUtils.isAppDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }
}