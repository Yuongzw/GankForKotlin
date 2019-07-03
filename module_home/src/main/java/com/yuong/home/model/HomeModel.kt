package com.yuong.home.model

import com.yuong.home.bean.HomeDataBean
import com.yuong.library_base.net.BaseLoadListener

interface HomeModel {
    /**
     * 今日最新干货
     *
     * @param loadListener
     */
    fun loadHomeData(loadListener: BaseLoadListener<Any>)
}