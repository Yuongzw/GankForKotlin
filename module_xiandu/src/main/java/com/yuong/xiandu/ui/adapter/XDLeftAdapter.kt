package com.yuong.xiandu.ui.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuong.xiandu.R
import com.yuong.xiandu.bean.XianduCategoryBean
import com.yuong.xiandu.databinding.XdLeftItemBinding


class XDLeftAdapter(context: Context) : BaseQuickAdapter<XianduCategoryBean.ResultsBean, BaseViewHolder>(null) {
    private var context: Context
    private var position = 0
    var onItemClickListener: OnItemClick? = null

    init {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<XdLeftItemBinding>(
            LayoutInflater.from(context),
            R.layout.xd_left_item,
            parent,
            false
        )
        return MyBaseViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding = DataBindingUtil.getBinding<XdLeftItemBinding>(holder.itemView)
        if (this.position == position) {
            binding!!.tvTreeLeftItem.setTextColor(Color.BLUE)
        } else {
            binding!!.tvTreeLeftItem.setTextColor(Color.BLACK)
        }
    }

    override fun convert(helper: BaseViewHolder?, item: XianduCategoryBean.ResultsBean?) {
        val binding = DataBindingUtil.getBinding<XdLeftItemBinding>(helper!!.itemView)
        binding!!.dataBean = item
        if (onItemClickListener != null) {
            binding.root.setOnClickListener {
                if (this.position != helper.position) {
                    notifyItemChanged(this.position)
                    this.position = helper.position
                    onItemClickListener!!.onItemClickListener(item!!.en_name!!)
                    notifyItemChanged(this.position)
                    this.position = helper.position
                }
            }
        }

    }

    inner class MyBaseViewHolder(view: View?) : BaseViewHolder(view)

    interface OnItemClick {
        fun onItemClickListener(en_name: String)
    }
}