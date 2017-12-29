package com.junlong0716.module.girls

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午4:09 2017/12/29
 *@modified by:
 */

@Route(path = "/module_girls/GirlsFragment")
class GirlsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_girls, container, false)
        return view
    }
}