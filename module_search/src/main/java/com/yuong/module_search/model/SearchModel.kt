package com.yuong.module_search.model

import com.yuong.library_base.net.BaseLoadListener

interface SearchModel {

    fun loadSearchData(category: String, page:Int, listener: BaseLoadListener<Any>)
}