package com.yuong.xiandu.ui.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.thefinestartist.finestwebview.FinestWebView
import com.yuong.xiandu.R
import com.yuong.xiandu.bean.XianduDataBean
import com.yuong.xiandu.databinding.XianduItemBinding

class XDRvAdapter(val context: Context): BaseQuickAdapter<XianduDataBean.ResultsBean, BaseViewHolder>(null) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<XianduItemBinding>(LayoutInflater.from(context), R.layout.xiandu_item, parent, false)
        return MyBaseViewHolder(binding.root)
    }
    override fun convert(helper: BaseViewHolder?, item: XianduDataBean.ResultsBean?) {
        val binding = DataBindingUtil.getBinding<XianduItemBinding>(helper!!.itemView)
        binding!!.xianduData = item
        binding.root.setOnClickListener {
            FinestWebView.Builder(context).show(item!!.url!!)

        }
    }

    inner class MyBaseViewHolder(view: View?) : BaseViewHolder(view)

}