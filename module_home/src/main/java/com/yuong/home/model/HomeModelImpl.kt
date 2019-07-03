package com.yuong.home.model

import android.content.Context
import com.google.gson.Gson
import com.yuong.home.bean.HomeDataBean
import com.yuong.library_base.base.BaseModel
import com.yuong.library_base.net.BaseLoadListener
import com.yuong.library_base.subscriber.PRSubscriber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class HomeModelImpl(context: Context): BaseModel(context), HomeModel {

    override fun loadHomeData(loadListener: BaseLoadListener<Any>) {
        api.getHomeData()
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
                    val homeDataBean = gson.fromJson(json, HomeDataBean::class.java)
                    loadListener.loadSuccess(homeDataBean)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    deleteDisposable(disposable!!)
                    loadListener.loadFailure(e.message!!)
                }
            })
    }
}