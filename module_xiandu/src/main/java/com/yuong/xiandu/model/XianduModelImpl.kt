package com.yuong.xiandu.model

import android.content.Context
import com.google.gson.Gson
import com.yuong.library_base.base.BaseModel
import com.yuong.library_base.net.BaseLoadListener
import com.yuong.library_base.subscriber.PRSubscriber
import com.yuong.xiandu.bean.XianduCategoryBean
import com.yuong.xiandu.bean.XianduDataBean
import com.yuong.xiandu.bean.XianduSubCategoryBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class XianduModelImpl(context: Context): BaseModel(context), XianduModel {

    override fun loadXianduSuperCategory(loadListener: BaseLoadListener<Any>) {
        api.getXianDuCategoryData()
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
                    val categoryBean = gson.fromJson(json, XianduCategoryBean::class.java)
                    loadListener.loadSuccess(categoryBean)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    deleteDisposable(disposable!!)
                    loadListener.loadFailure(e.message!!)
                }
            })
    }

    override fun loadXianduSubCategory(category: String, loadListener: BaseLoadListener<Any>) {
        api.getXianDuCategorysData(category)
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
                    val subCategoryBean = gson.fromJson(json, XianduSubCategoryBean::class.java)
                    loadListener.loadSuccess(subCategoryBean)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    deleteDisposable(disposable!!)
                    loadListener.loadFailure(e.message!!)
                }
            })
    }

    override fun loadXianduData(id: String, page: Int, loadListener: BaseLoadListener<Any>) {
        api.getXianDuData(id, page)
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
                    val dataBean = gson.fromJson(json, XianduDataBean::class.java)
                    loadListener.loadSuccess(dataBean)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    deleteDisposable(disposable!!)
                    loadListener.loadFailure(e.message!!)
                }
            })
    }
}