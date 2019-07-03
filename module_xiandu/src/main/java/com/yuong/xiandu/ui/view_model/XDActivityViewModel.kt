package com.yuong.xiandu.ui.view_model

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.yuong.library_base.base.BaseViewModel
import com.yuong.library_base.net.BaseLoadListener
import com.yuong.xiandu.R
import com.yuong.xiandu.bean.XianduDataBean
import com.yuong.xiandu.databinding.ActivityXianduBinding
import com.yuong.xiandu.model.XianduModel
import com.yuong.xiandu.model.XianduModelImpl
import com.yuong.xiandu.ui.adapter.XDRvAdapter

class XDActivityViewModel: BaseViewModel<ActivityXianduBinding>(), BaseLoadListener<Any> {
    private lateinit var model: XianduModel
    private lateinit var adapter: XDRvAdapter
    private lateinit var skeletonScreen: SkeletonScreen
    private var currentPage = 1
    private var isFirst = true
    private var isRefresh = false
    private val itemList = ArrayList<XianduDataBean.ResultsBean>()
    var en_name = ""

    override fun initUi() {
        initRecyclerView()
        initListener()
        initNet()
    }

    private fun initListener() {
        binding!!.xianduSmartRefreshLayout.setOnLoadMoreListener {
            isRefresh = false
            model.loadXianduData(en_name, ++currentPage, this)
        }
        binding!!.xianduSmartRefreshLayout.setOnRefreshListener {
            currentPage = 1
            isRefresh = true
            model.loadXianduData(en_name, currentPage, this)
        }
    }

    private fun initRecyclerView() {
        adapter = XDRvAdapter(activity!!)
        binding!!.xianduRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding!!.xianduRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        //骨架屏
        skeletonScreen = Skeleton.bind(binding!!.xianduRecyclerView)
            .adapter(adapter)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1200)
            .count(10)
            .load(R.layout.xiandu_item_skeleton)
            .show()
    }

    override fun initNet() {
        model = XianduModelImpl(activity!!.applicationContext)
        model.loadXianduData(en_name, currentPage, this)
    }

    override fun loadSuccess(t: Any) {
        if (isFirst) {
            isFirst = false
            skeletonScreen.hide()
        }
        if (isRefresh) {
            binding!!.xianduSmartRefreshLayout.finishRefresh()
        } else {
            binding!!.xianduSmartRefreshLayout.finishLoadMore()
        }
        itemList.addAll((t as XianduDataBean).results!!)
        adapter.setNewData(itemList)
    }

    override fun loadFailure(message: String) {
    }

    override fun loadStart() {
    }

    override fun loadComplete() {
    }
}