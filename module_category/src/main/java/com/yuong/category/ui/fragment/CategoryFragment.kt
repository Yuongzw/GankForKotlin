package com.yuong.category.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.yuong.category.R
import com.yuong.category.databinding.FragmentCategoryBinding
import com.yuong.category.ui.view_model.CategoryViewModel
import com.yuong.library_base.base.BaseFragment
import com.yuong.library_base.di.component.FragmentComponent
import com.yuong.library_base.router.RouterFragmentPath

@Route(path = RouterFragmentPath.Category.PAGE_CATEGORY)
class CategoryFragment: BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {
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
        return R.layout.fragment_category
    }
}