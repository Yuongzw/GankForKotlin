package com.yuong.home.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.yuong.home.DaggerHomeFragmentComponent
import com.yuong.home.R
import com.yuong.home.databinding.FragmentHomeBinding
import com.yuong.library_base.base.BaseFragment
import com.yuong.home.ui.view_model.HomeViewModel
import com.yuong.library_base.base.MyApplication
import com.yuong.library_base.di.component.FragmentComponent
import com.yuong.library_base.di.module.FragmentModule
import com.yuong.library_base.router.RouterFragmentPath

@Route(path = RouterFragmentPath.Home.PAGER_HOME)
class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun initFragmentComponent(): FragmentComponent? {
        return DaggerHomeFragmentComponent.builder()
            .applicationComponent((activity!!.application as MyApplication).getApplicationComponent())
            .fragmentModule(FragmentModule(this))
            .build()
    }

    override fun initInjector() {
        (mFragmentComponent as DaggerHomeFragmentComponent).inject(this)
    }


    override fun initView() {
        viewModel.initUi()
    }

    override fun loadData() {
        viewModel.initNet()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_home
    }
}