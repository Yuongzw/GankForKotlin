package com.yuong.welfare.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.yuong.library_base.base.BaseFragment
import com.yuong.library_base.di.component.FragmentComponent
import com.yuong.library_base.router.RouterFragmentPath
import com.yuong.welfare.R
import com.yuong.welfare.databinding.FragmentWelfareBinding
import com.yuong.welfare.ui.view_model.WelfareViewModel

@Route(path = RouterFragmentPath.Welfare.PAGE_WELFARE)
class WelfareFragment: BaseFragment<FragmentWelfareBinding , WelfareViewModel>() {
    override fun initFragmentComponent(): FragmentComponent? {
        return null
    }

    override fun initInjector() {
    }

    override fun initView() {
        viewModel.initUi()
    }

    override fun loadData() {
        viewModel.initNet()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_welfare
    }
}