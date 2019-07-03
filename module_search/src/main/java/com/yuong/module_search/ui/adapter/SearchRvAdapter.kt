package com.yuong.module_search.ui.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.thefinestartist.finestwebview.FinestWebView
import com.yuong.module_search.R
import com.yuong.module_search.bean.SearchDataBean
import com.yuong.module_search.databinding.SearchItemBinding


class SearchRvAdapter(val context: Context): BaseQuickAdapter<SearchDataBean.ResultsBean, BaseViewHolder>(null) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<SearchItemBinding>(LayoutInflater.from(context), R.layout.search_item, parent, false)
        return MyBaseViewHolder(binding.root)
    }
    override fun convert(helper: BaseViewHolder?, item: SearchDataBean.ResultsBean?) {
        val binding = DataBindingUtil.getBinding<SearchItemBinding>(helper!!.itemView)
        binding!!.searchBean = item
        binding.root.setOnClickListener {
            FinestWebView.Builder(context).show(item!!.url!!)

        }
    }

    inner class MyBaseViewHolder(view: View?) : BaseViewHolder(view)

}