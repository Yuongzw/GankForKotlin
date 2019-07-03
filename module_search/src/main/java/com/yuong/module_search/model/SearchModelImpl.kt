package com.yuong.module_search.model

import android.content.Context
import com.google.gson.Gson
import com.yuong.library_base.base.BaseModel
import com.yuong.library_base.net.BaseLoadListener
import com.yuong.library_base.subscriber.PRSubscriber
import com.yuong.module_search.bean.SearchDataBean
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class SearchModelImpl(context: Context): BaseModel(context), SearchModel {

    override fun loadSearchData(category: String, page: Int, listener: BaseLoadListener<Any>) {
        api.searchData(category, page)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : PRSubscriber<ResponseBody>() {
                override fun onSubscribe(d: Disposable) {
                    super.onSubscribe(d)
                    addDisposable(d)
                }

                override fun onNext(t: ResponseBody) {
                    val gson = Gson()
                    val json = t.string()
                    val searchDataBean = gson.fromJson(json, SearchDataBean::class.java)
                    listener.loadSuccess(searchDataBean)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    deleteDisposable(disposable!!)
                    listener.loadFailure(e.message!!)
                }
            })
    }
}