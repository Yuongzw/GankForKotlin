package com.yuong.xiandu.model

import com.yuong.library_base.net.BaseLoadListener

interface XianduModel {
    /**
     * 获取闲读的主分类
     */
    fun loadXianduSuperCategory(loadListener: BaseLoadListener<Any>)

    /**
     * 获取闲读的子分类
     */
    fun loadXianduSubCategory(category: String, loadListener: BaseLoadListener<Any>)

    /**
     * 获取闲读数据
     */
    fun loadXianduData(id:String, page: Int, loadListener: BaseLoadListener<Any>)
}