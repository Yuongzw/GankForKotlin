package com.yuong.category.ui.view_model

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.yuong.category.R
import com.yuong.category.bean.CategoryBean
import com.yuong.category.databinding.FragmentCategoryBinding
import com.yuong.category.databinding.FragmentCategoryDetailBinding
import com.yuong.category.model.CategoryModel
import com.yuong.category.model.CategoryModelImpl
import com.yuong.category.ui.adapter.CategoryRvAdapter
import com.yuong.library_base.base.BaseViewModel
import com.yuong.library_base.net.BaseLoadListener

class CategoryDetailViewModel: BaseViewModel<FragmentCategoryDetailBinding>(), BaseLoadListener<Any> {

    private lateinit var model: CategoryModel
    private lateinit var adapter: CategoryRvAdapter
    private lateinit var skeletonScreen: SkeletonScreen
    private var currentPage = 1
    private var isFirst = true
    private var isRefresh = false
    private val itemList = ArrayList<CategoryBean.ResultsBean>()
    var type = ""

    override fun initUi() {
        initRecyclerView()
        initListener()
    }

    override fun initNet() {
        model = CategoryModelImpl(activity!!.applicationContext)
        model.loadCategoryData(type, currentPage, this)
    }

    private fun initRecyclerView() {
        adapter = CategoryRvAdapter(activity!!)
        binding!!.categoryRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding!!.categoryRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
//        binding!!.categoryRecyclerView.adapter = adapter
        //骨架屏
        skeletonScreen = Skeleton.bind(binding!!.categoryRecyclerView)
            .adapter(adapter)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1200)
            .count(10)
            .load(R.layout.category_item_skeleton)
            .show()
    }

    fun initListener() {
        binding!!.categorySmartRefreshLayout.setOnRefreshListener {
            currentPage = 1
            isRefresh = true
            itemList.clear()
            model.loadCategoryData(type, currentPage, this)
        }

        binding!!.categorySmartRefreshLayout.setOnLoadMoreListener {
            isRefresh = false
            model.loadCategoryData(type, ++currentPage, this)
        }
    }

    override fun loadSuccess(t: Any) {
        itemList.addAll((t as CategoryBean).results!!)
        if (isFirst) {
            isFirst = false
            skeletonScreen.hide()
        }
        if (isRefresh) {
            binding!!.categorySmartRefreshLayout.finishRefresh()
        } else{
            binding!!.categorySmartRefreshLayout.finishLoadMore()
        }
        adapter.setNewData(itemList)
    }

    override fun loadFailure(message: String) {
    }

    override fun loadStart() {
    }

    override fun loadComplete() {
    }
}