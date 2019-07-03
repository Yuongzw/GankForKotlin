package com.yuong.xiandu.ui.adapter

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuong.library_base.utils.ToastUtils
import com.yuong.xiandu.R
import com.yuong.xiandu.bean.XianduSubCategoryBean
import com.yuong.xiandu.databinding.XdRightItemBinding
import com.yuong.xiandu.ui.activity.XianduActivity


class XDRightAdapter(context: Context) : BaseQuickAdapter<XianduSubCategoryBean.ResultsBean, BaseViewHolder>(null) {
    private var context: Context

    init {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<XdRightItemBinding>(LayoutInflater.from(context), R.layout.xd_right_item, parent, false)
        return MyBaseViewHolder(binding.root)
    }

    override fun convert(helper: BaseViewHolder?, item: XianduSubCategoryBean.ResultsBean?) {
        val binding = DataBindingUtil.getBinding<XdRightItemBinding>(helper!!.itemView)
        binding!!.dataBean = item
        helper.itemView.setOnClickListener { view ->
            val intent = Intent(context, XianduActivity::class.java)
            intent.putExtra("id", item!!.id)
            intent.putExtra("title", item.title)
            context.startActivity(intent)
            ToastUtils.showShort(item.title + "  " + item.id)
        }

    }

    inner class MyBaseViewHolder(view: View?) : BaseViewHolder(view)
}