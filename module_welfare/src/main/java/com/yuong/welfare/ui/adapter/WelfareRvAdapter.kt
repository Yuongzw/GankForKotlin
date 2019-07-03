package com.yuong.welfare.ui.adapter

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuong.welfare.R
import com.yuong.welfare.bean.WelfareBean
import com.yuong.welfare.databinding.WelfareItemBinding
import com.yuong.welfare.ui.ImageActivity
import android.support.v4.app.ActivityOptionsCompat



class WelfareRvAdapter(val context: Context): BaseQuickAdapter<WelfareBean.ResultsBean, BaseViewHolder>(null) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<WelfareItemBinding>(LayoutInflater.from(context), R.layout.welfare_item, parent, false)
        return MyBaseViewHolder(binding.root)
    }
    override fun convert(helper: BaseViewHolder?, item: WelfareBean.ResultsBean?) {
        val binding = DataBindingUtil.getBinding<WelfareItemBinding>(helper!!.itemView)
        binding!!.welfareBean = item
        Glide.with(context)
            .load(item!!.url)
            .placeholder(R.drawable.default_project_img)
            .error(R.drawable.default_project_img)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.welfareImageView)
        binding.root.setOnClickListener {

            val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(
                    context as FragmentActivity,
                    binding.welfareImageView,
                    "image"
                )

            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra("url", item.url)
            intent.putExtra("date", item.publishedAt)
            (context as FragmentActivity).startActivity(intent, options.toBundle())
//            (context as FragmentActivity).startActivity(intent)
        }

    }

    inner class MyBaseViewHolder(view: View?) : BaseViewHolder(view)

}