package com.yuong.category.ui.view_model

import com.yuong.category.databinding.FragmentCategoryBinding
import com.yuong.category.ui.adapter.CategoryPagerAdapter
import com.yuong.library_base.base.BaseViewModel

class CategoryViewModel: BaseViewModel<FragmentCategoryBinding>() {

    lateinit var adapter: CategoryPagerAdapter

    override fun initUi() {
        adapter = CategoryPagerAdapter(activity!!.supportFragmentManager)
        binding!!.categoryViewPager.adapter = adapter
        binding!!.categoryTabLayout.setupWithViewPager(binding!!.categoryViewPager)

    }

    override fun initNet() {
    }
}