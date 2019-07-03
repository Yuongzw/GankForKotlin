package com.yuong.library_base.net

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface GankApi {
    //今日最新干货
    @GET("today")
    fun getHomeData(): Observable<ResponseBody>

    //获取闲读的主分类
    @GET("xiandu/categories")
    fun getXianDuCategoryData(): Observable<ResponseBody>

    //获取闲读的子分类
    @GET("xiandu/category/{en_name}")
    fun getXianDuCategorysData(@Path("en_name") en_name: String): Observable<ResponseBody>

    //获取闲读数据
    /*
        id 后面可接受参数为子分类返回的id
        page 第几页，从1开始
        count 每页的个数
     */
    @GET("xiandu/data/id/{id}/count/10/page/{page}")
    fun getXianDuData(@Path("id") id: String, @Path("page") page: Int): Observable<ResponseBody>

    //搜索 API
    @GET("search/query/listview/category/{category}/count/10/page/{page}")
    fun searchData(@Path("category") category: String, @Path("page") page: Int): Observable<ResponseBody>

    //福利
    @GET("data/福利/10/{page}")
    fun getWelfareData(@Path("page") page: Int): Observable<ResponseBody>

    //分类数据
    @GET("data/{type}/10/{page}")
    fun getCategoryData(@Path("type") type: String, @Path("page") page: Int):Observable<ResponseBody>


}