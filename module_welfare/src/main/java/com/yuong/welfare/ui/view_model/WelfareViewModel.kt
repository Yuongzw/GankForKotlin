package com.yuong.welfare.ui.view_model

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.yuong.library_base.base.BaseViewModel
import com.yuong.library_base.net.BaseLoadListener
import com.yuong.welfare.bean.WelfareBean
import com.yuong.welfare.databinding.FragmentWelfareBinding
import com.yuong.welfare.model.WelfareModel
import com.yuong.welfare.model.WelfareModelImpl
import com.yuong.welfare.ui.adapter.WelfareRvAdapter
import android.support.v7.widget.StaggeredGridLayoutManager
import com.yuong.welfare.R


class WelfareViewModel: BaseViewModel<FragmentWelfareBinding>(), BaseLoadListener<Any> {
    private lateinit var model: WelfareModel
    private lateinit var adapter: WelfareRvAdapter
    private lateinit var skeletonScreen: SkeletonScreen
    private val itemList = ArrayList<WelfareBean.ResultsBean>()
    private val type = "福利"
    private var currentPage = 1
    private var isFirst = true
    private var isRefresh = false

    override fun initUi() {
        initRecyclerView()
        initListener()
    }

    override fun initNet() {
        model = WelfareModelImpl(activity!!.applicationContext)
        model.loadWelfareData(type, currentPage, this)
    }

    private fun initRecyclerView() {
        adapter = WelfareRvAdapter(activity!!)
//        binding!!.welfareRecyclerView.layoutManager = LinearLayoutManager(activity)
        //使用瀑布流布局,第一个参数 spanCount 列数,第二个参数 orentation 排列方向
        val recyclerViewLayoutManager = GridLayoutManager(activity, 2)
        binding!!.welfareRecyclerView.layoutManager = recyclerViewLayoutManager
//        binding!!.welfareRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
//        binding!!.welfareRecyclerView.adapter = adapter
        //骨架屏
        skeletonScreen = Skeleton.bind(binding!!.welfareRecyclerView)
            .adapter(adapter)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1200)
            .count(10)
            .load(R.layout.welfare_item_skeleton)
            .show()
    }

    fun initListener() {
        binding!!.welfareSmartRefreshLayout.setOnRefreshListener {
            currentPage = 1
            isRefresh = true
            itemList.clear()
            model.loadWelfareData(type, currentPage, this)
        }

        binding!!.welfareSmartRefreshLayout.setOnLoadMoreListener {
            model.loadWelfareData(type, ++currentPage, this)
        }
    }

    override fun loadSuccess(t: Any) {
        itemList.addAll((t as WelfareBean).results!!)
        if (isFirst) {
            isFirst = false
            skeletonScreen.hide()
        }
        if (isRefresh) {
            binding!!.welfareSmartRefreshLayout.finishRefresh()
        } else{
            binding!!.welfareSmartRefreshLayout.finishLoadMore()
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