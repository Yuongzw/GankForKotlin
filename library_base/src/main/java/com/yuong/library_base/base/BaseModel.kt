package com.yuong.library_base.base

import android.content.Context
import com.yuong.library_base.net.GankApi
import com.yuong.library_base.net.MyRetrofit

open class BaseModel(context: Context) : DefaultDisposablePoolImpl(){

    protected var api: GankApi
    init {
        api = MyRetrofit.getInstance(context).api!!
    }
}