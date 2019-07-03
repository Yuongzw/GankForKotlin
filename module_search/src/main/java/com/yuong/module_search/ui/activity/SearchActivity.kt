package com.yuong.module_search.ui.activity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.yuong.library_base.base.BaseActivity
import com.yuong.library_base.router.RouterActivityPath
import com.yuong.module_search.R
import com.yuong.module_search.databinding.ActivitySeachBinding
import com.yuong.module_search.ui.view_model.SearchViewModel

@Route(path = RouterActivityPath.Search.PAGER_SEARCH)
class SearchActivity: BaseActivity<ActivitySeachBinding, SearchViewModel>() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_seach
    }

    override fun initData() {
        binding!!.viewModel = viewModel
        binding!!.searchTitle.findViewById<TextView>(R.id.tv_title).text = "搜索"
        binding!!.searchTitle.findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
        binding!!.searchTitle.findViewById<ImageView>(R.id.iv_search).visibility = View.GONE
        viewModel!!.initUi()
    }
}