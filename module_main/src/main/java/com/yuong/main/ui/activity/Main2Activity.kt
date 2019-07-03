package com.yuong.main.ui.activity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.yuong.library_base.base.BaseActivity
import com.yuong.library_base.utils.ToastUtils
import com.yuong.main.R
import com.yuong.main.databinding.ActivityMain2Binding
import com.yuong.main.ui.view_model.Main2ViewModel

class Main2Activity: BaseActivity<ActivityMain2Binding, Main2ViewModel>() {
    private var mPressedTime = 0L

    override fun getLayoutResource(): Int {
        return R.layout.activity_main2
    }

    override fun initData() {
        binding!!.titleBar.findViewById<ImageView>(R.id.iv_back).visibility = View.GONE
        viewModel!!.initUi()
    }

    override fun onBackPressed() {
        val mNowTime = System.currentTimeMillis()//获取第一次按下的时间
        if((mNowTime - mPressedTime) > 2000) {//比较两次按下的时间差
            ToastUtils.showShort("再按一次退出程序！")
            mPressedTime = mNowTime
        } else{
            finish()
            System.exit(0)
        }
    }
}