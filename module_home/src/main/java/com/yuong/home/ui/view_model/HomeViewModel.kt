package com.yuong.home.ui.view_model

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.yuong.home.R
import com.yuong.home.bean.HomeDataBean
import com.yuong.home.databinding.FragmentHomeBinding
import com.yuong.home.model.HomeModel
import com.yuong.home.model.HomeModelImpl
import com.yuong.home.ui.adapter.HomeDataAdapter
import com.yuong.library_base.base.BaseViewModel
import com.yuong.library_base.net.BaseLoadListener

class HomeViewModel : BaseViewModel<FragmentHomeBinding>(), BaseLoadListener<Any> {
    private lateinit var homeModel: HomeModel

    private lateinit var adapter: HomeDataAdapter

    override fun initUi() {
        initRecyclerView()


    }

    private var skeletonScreen: SkeletonScreen? = null

    private fun initRecyclerView() {
        adapter = HomeDataAdapter(activity!!)
        binding!!.homeRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding!!.homeRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
//        binding!!.homeRecyclerView.adapter = adapter
        //骨架屏
        skeletonScreen = Skeleton.bind(binding!!.homeRecyclerView)
            .adapter(adapter)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1200)
            .count(10)
            .load(R.layout.home_item_skeleton)
            .show()


    }

    override fun initNet() {
        homeModel = HomeModelImpl(activity!!.applicationContext)
        homeModel.loadHomeData(this)
    }

    override fun loadSuccess(t: Any) {
        val homeBean = t as HomeDataBean
        adapter.setNewData(homeBean.results!!.Android)
        skeletonScreen!!.hide()

    }

    override fun loadFailure(message: String) {
    }

    override fun loadStart() {
    }

    override fun loadComplete() {
    }
}