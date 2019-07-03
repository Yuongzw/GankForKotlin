package com.yuong.category.ui.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.thefinestartist.finestwebview.FinestWebView
import com.yuong.category.R
import com.yuong.category.bean.CategoryBean
import com.yuong.category.databinding.CategoryItemBinding

class CategoryRvAdapter(val context: Context): BaseQuickAdapter<CategoryBean.ResultsBean, BaseViewHolder>(null) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<CategoryItemBinding>(LayoutInflater.from(context), R.layout.category_item, parent, false)
        return MyBaseViewHolder(binding.root)
    }
    override fun convert(helper: BaseViewHolder?, item: CategoryBean.ResultsBean?) {
        val binding = DataBindingUtil.getBinding<CategoryItemBinding>(helper!!.itemView)
        binding!!.categoryBean = item
        binding.root.setOnClickListener {
            FinestWebView.Builder(context).show(item!!.url!!)

        }
    }

    inner class MyBaseViewHolder(view: View?) : BaseViewHolder(view)

}