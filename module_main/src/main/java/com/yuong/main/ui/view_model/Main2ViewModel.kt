package com.yuong.main.ui.view_model

import android.content.Intent
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.yuong.library_base.base.BaseActivity
import com.yuong.library_base.base.BaseFragment
import com.yuong.library_base.base.BaseViewModel
import com.yuong.library_base.router.RouterActivityPath
import com.yuong.library_base.router.RouterFragmentPath
import com.yuong.main.R
import com.yuong.main.databinding.ActivityMain2Binding
import com.yuong.main.ui.adapter.MainVPAdapter
import java.util.ArrayList

class Main2ViewModel : BaseViewModel<ActivityMain2Binding>() {
    private lateinit var adapter: MainVPAdapter
    private var mFragments = ArrayList<BaseFragment<*, *>>()

    override fun initUi() {
        initFragments()
        initListener()
    }

    fun initFragments() {
        //ARouter拿到多Fragment(这里需要通过ARouter获取，不能直接new,因为在组件独立运行时，宿主app是没有依赖其他组件，所以new不到其他组件的Fragment)
        val homeFragment =
            ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation() as BaseFragment<*, *>
        val categoryFragment =
            ARouter.getInstance().build(RouterFragmentPath.Category.PAGE_CATEGORY).navigation() as BaseFragment<*, *>
        val welfareFragment =
            ARouter.getInstance().build(RouterFragmentPath.Welfare.PAGE_WELFARE).navigation() as BaseFragment<*, *>
        val leisureFragment =
            ARouter.getInstance().build(RouterFragmentPath.LeisureReading.PAGE_LEISUREREADING).navigation() as BaseFragment<*, *>
        mFragments.add(homeFragment)
        mFragments.add(categoryFragment)
        mFragments.add(welfareFragment)
        mFragments.add(leisureFragment)
        adapter = MainVPAdapter(activity!!.getSupportFragmentManager(), mFragments)
        binding!!.main2ViewPager.adapter = adapter
    }

    fun initListener() {
        binding!!.bottomBar2.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.tab_home ->{
                    binding!!.main2ViewPager.currentItem = 0
                    binding!!.titleBar.findViewById<TextView>(R.id.tv_title).text = "首页"
                }
                R.id.tab_tree ->{
                    binding!!.main2ViewPager.currentItem = 1
                    binding!!.titleBar.findViewById<TextView>(R.id.tv_title).text = "分类"
                }
                R.id.tab_wechat -> {
                    binding!!.main2ViewPager.currentItem = 2
                    binding!!.titleBar.findViewById<TextView>(R.id.tv_title).text = "福利"
                }
                R.id.tab_project ->{
                    binding!!.main2ViewPager.currentItem = 3
                    binding!!.titleBar.findViewById<TextView>(R.id.tv_title).text = "闲读"
                }
            }
        }
        binding!!.titleBar.findViewById<ImageView>(R.id.iv_search).setOnClickListener {
            ARouter.getInstance().build(RouterActivityPath.Search.PAGER_SEARCH)
                .navigation()
        }

        binding!!.main2ViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {

            }

            override fun onPageSelected(position: Int) {
                binding!!.bottomBar2.selectTabAtPosition(position)
            }

            override fun onPageScrollStateChanged(i: Int) {

            }
        })
    }

    override fun initNet() {
    }
}