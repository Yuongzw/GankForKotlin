package com.yuong.category.ui.fragment

import com.yuong.category.R
import com.yuong.category.databinding.FragmentCategoryDetailBinding
import com.yuong.category.ui.view_model.CategoryDetailViewModel
import com.yuong.library_base.base.BaseFragment
import com.yuong.library_base.di.component.FragmentComponent

class CategoryDetailFragment: BaseFragment<FragmentCategoryDetailBinding, CategoryDetailViewModel>() {
    override fun initFragmentComponent(): FragmentComponent? {
        return null
    }

    override fun initInjector() {
    }

    override fun initView() {
        viewModel.initUi()
    }

    override fun loadData() {
        val bundle = arguments!!
        viewModel.type = bundle.getString("type")
        viewModel.initNet()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_category_detail
    }
}