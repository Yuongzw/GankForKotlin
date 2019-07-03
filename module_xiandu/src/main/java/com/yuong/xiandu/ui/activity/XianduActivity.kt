package com.yuong.xiandu.ui.activity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.yuong.library_base.base.BaseActivity
import com.yuong.xiandu.R
import com.yuong.xiandu.databinding.ActivityXianduBinding
import com.yuong.xiandu.ui.view_model.XDActivityViewModel

class XianduActivity: BaseActivity<ActivityXianduBinding, XDActivityViewModel>() {
    private var tv_title: TextView? = null
    private var iv_back: ImageView? = null
    private var iv_search: ImageView? = null
    override fun getLayoutResource(): Int {
        return R.layout.activity_xiandu
    }

    override fun initData() {
        tv_title = findViewById(R.id.tv_title)
        iv_back = findViewById(R.id.iv_back)
        iv_search = findViewById(R.id.iv_search)
        iv_search!!.visibility = View.GONE
        iv_back!!.setOnClickListener { finish() }
        val en_name = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        tv_title!!.text = title
        viewModel!!.en_name = en_name
        viewModel!!.initUi()


    }
}