package com.yuong.module_search.ui.view_model

import android.databinding.ObservableField
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.yuong.library_base.base.BaseViewModel
import com.yuong.library_base.net.BaseLoadListener
import com.yuong.module_search.R
import com.yuong.module_search.bean.SearchDataBean
import com.yuong.module_search.databinding.ActivitySeachBinding
import com.yuong.module_search.model.SearchModel
import com.yuong.module_search.model.SearchModelImpl
import com.yuong.module_search.ui.adapter.SearchRvAdapter

class SearchViewModel : BaseViewModel<ActivitySeachBinding>(), BaseLoadListener<Any> {
    private lateinit var model: SearchModel
    private lateinit var adapter: SearchRvAdapter
    //关键字
    var searchKey = ObservableField("")
    private var page = 1
    private var isFirst: Boolean = true
    private var isRefresh = false
    private var dataList = ArrayList<SearchDataBean.ResultsBean>()
    private lateinit var skeletonScreen: SkeletonScreen

    override fun initUi() {

        initRecyclerView()
        initListener()
        initNet()
    }

    private fun initListener() {
        binding!!.searchSmartRefreshLayout.setOnRefreshListener {
            page = 1
            isRefresh = true
            model.loadSearchData(searchKey.get()!!, page, this)
        }

        binding!!.searchSmartRefreshLayout.setOnLoadMoreListener {
            isRefresh = false
            model.loadSearchData(searchKey.get()!!, ++page, this)
        }

        binding!!.tvSearch.setOnClickListener {
            //骨架屏
            skeletonScreen = Skeleton.bind(binding!!.searchRecyclerView)
                .adapter(adapter)
                .shimmer(true)
                .angle(20)
                .frozen(false)
                .duration(1200)
                .count(10)
                .load(R.layout.search_item_skeleton)
                .show()
            isFirst = true
            page = 1
            dataList.clear()
            model.loadSearchData(searchKey.get()!!, page, this)
        }

        binding!!.searchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(textView: TextView?, actionId: Int, keyEvent: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //骨架屏
                    skeletonScreen = Skeleton.bind(binding!!.searchRecyclerView)
                        .adapter(adapter)
                        .shimmer(true)
                        .angle(20)
                        .frozen(false)
                        .duration(1200)
                        .count(10)
                        .load(R.layout.search_item_skeleton)
                        .show()
                    isFirst = true
                    page = 1
                    dataList.clear()
                    model.loadSearchData(searchKey.get()!!, page, this@SearchViewModel)
                    return true
                }
                return false
            }

        })
    }

    private fun initRecyclerView() {
        adapter = SearchRvAdapter(activity!!)
        binding!!.searchRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding!!.searchRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))


    }

    override fun initNet() {
        model = SearchModelImpl(activity!!.applicationContext)
    }

    override fun loadSuccess(t: Any) {
        if (isFirst) {
            skeletonScreen.hide()
        }
        if (isRefresh) {
            binding!!.searchSmartRefreshLayout.finishRefresh()
        } else {
            binding!!.searchSmartRefreshLayout.finishLoadMore()
        }
        dataList.addAll((t as SearchDataBean).results!!)
        adapter.setNewData(dataList)
    }

    override fun loadFailure(message: String) {
    }

    override fun loadStart() {
    }

    override fun loadComplete() {
    }
}