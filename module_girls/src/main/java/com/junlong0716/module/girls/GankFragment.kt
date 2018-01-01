package com.junlong0716.module.girls

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午1:15 2018/1/1
 *@modified by:
 */
class GankFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_girls_list,container,false)

        return view
    }
}