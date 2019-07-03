package com.yuong.category.model

import android.content.Context
import com.google.gson.Gson
import com.yuong.category.bean.CategoryBean
import com.yuong.library_base.base.BaseModel
import com.yuong.library_base.net.BaseLoadListener
import com.yuong.library_base.subscriber.PRSubscriber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class CategoryModelImpl(context: Context): BaseModel(context), CategoryModel  {

    override fun loadCategoryData(type: String, page: Int, loadListener: BaseLoadListener<Any>) {
        api.getCategoryData(type, page)
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
                    val categoryBean = gson.fromJson(json, CategoryBean::class.java)
                    loadListener.loadSuccess(categoryBean)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    deleteDisposable(disposable!!)
                    loadListener.loadFailure(e.message!!)
                }
            })
    }
}