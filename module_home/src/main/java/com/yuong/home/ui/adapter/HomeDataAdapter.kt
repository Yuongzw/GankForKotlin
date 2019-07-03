package com.yuong.home.ui.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.thefinestartist.finestwebview.FinestWebView
import com.yuong.home.R
import com.yuong.home.bean.HomeDataBean
import com.yuong.home.databinding.HomeItemBinding

class HomeDataAdapter(val context: Context): BaseQuickAdapter<HomeDataBean.ResultsBean.AndroidBean, BaseViewHolder>(null) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<HomeItemBinding>(LayoutInflater.from(context), R.layout.home_item, parent, false)
        return MyBaseViewHolder(binding.root)
    }
    override fun convert(helper: BaseViewHolder?, item: HomeDataBean.ResultsBean.AndroidBean?) {
        val binding = DataBindingUtil.getBinding<HomeItemBinding>(helper!!.itemView)
        binding!!.androidBean = item
        binding.root.setOnClickListener {
            FinestWebView.Builder(context).show(item!!.url!!)

        }
    }

    inner class MyBaseViewHolder(view: View?) : BaseViewHolder(view)

}