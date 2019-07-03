package com.yuong.category.model

import com.yuong.library_base.net.BaseLoadListener

interface CategoryModel{

    /**
     * 获取分类数据
     */
    fun loadCategoryData(type: String, page: Int, loadListener: BaseLoadListener<Any>)

}