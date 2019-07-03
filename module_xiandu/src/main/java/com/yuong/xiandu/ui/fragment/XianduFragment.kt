package com.yuong.xiandu.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.yuong.library_base.base.BaseFragment
import com.yuong.library_base.di.component.FragmentComponent
import com.yuong.library_base.router.RouterFragmentPath
import com.yuong.xiandu.R
import com.yuong.xiandu.databinding.FragmentXianduBinding
import com.yuong.xiandu.ui.view_model.XDFragmentViewModel

@Route(path = RouterFragmentPath.LeisureReading.PAGE_LEISUREREADING)
class XianduFragment: BaseFragment<FragmentXianduBinding, XDFragmentViewModel>() {
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
        return R.layout.fragment_xiandu
    }
}