package com.yuong.welfare.model

import com.yuong.library_base.net.BaseLoadListener

interface WelfareModel{

    /**
     * 获取分类数据
     */
    fun loadWelfareData(type: String, page: Int, loadListener: BaseLoadListener<Any>)

}