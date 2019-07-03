package com.yuong.main.ui.activity

import com.yuong.library_base.base.BaseActivity
import com.yuong.main.R
import com.yuong.main.databinding.ActivityMainBinding
import com.yuong.main.ui.view_model.MainViewModel

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel> () {

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        viewModel!!.initUi()
    }
}