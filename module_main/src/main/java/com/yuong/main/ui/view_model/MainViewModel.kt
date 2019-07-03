package com.yuong.main.ui.view_model

import android.support.v4.view.ViewPager
import com.alibaba.android.arouter.launcher.ARouter
import com.yuong.library_base.base.BaseFragment
import com.yuong.library_base.base.BaseViewModel
import com.yuong.library_base.router.RouterFragmentPath
import com.yuong.main.databinding.ActivityMainBinding
import com.yuong.main.ui.adapter.MainVPAdapter
import java.util.ArrayList

class MainViewModel : BaseViewModel<ActivityMainBinding>() {
    private lateinit var adapter: MainVPAdapter
    private var mFragments = ArrayList<BaseFragment<*, *>>()

    override fun initUi() {
        initFragments()
    }

    fun initFragments() {
        //ARouter拿到多Fragment(这里需要通过ARouter获取，不能直接new,因为在组件独立运行时，宿主app是没有依赖其他组件，所以new不到其他组件的Fragment)
        val homeFragment =
            ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation() as BaseFragment<*, *>
        val treeFragment =
            ARouter.getInstance().build(RouterFragmentPath.Category.PAGE_CATEGORY).navigation() as BaseFragment<*, *>
        val wxFragment =
            ARouter.getInstance().build(RouterFragmentPath.Welfare.PAGE_WELFARE).navigation() as BaseFragment<*, *>
        val projectFragment =
            ARouter.getInstance().build(RouterFragmentPath.LeisureReading.PAGE_LEISUREREADING).navigation() as BaseFragment<*, *>
        mFragments.add(homeFragment)
        mFragments.add(treeFragment)
        mFragments.add(wxFragment)
        mFragments.add(projectFragment)
        adapter = MainVPAdapter(activity!!.getSupportFragmentManager(), mFragments)
        binding!!.mainViewPager.adapter = adapter
    }

    fun initListener() {
//        binding!!.bottomBar.setOnTabSelectListener { tabId ->
//            when (tabId) {
//                R.id.tab_home -> binding!!.mainViewPager.currentItem = 0
//                R.id.tab_tree -> binding!!.mainViewPager.currentItem = 1
//                R.id.tab_wechat -> binding!!.mainViewPager.currentItem = 2
//                R.id.tab_project -> binding!!.mainViewPager.currentItem = 3
//            }
//        }

        binding!!.mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {

            }

            override fun onPageSelected(position: Int) {
                binding!!.bottomBar.selectTabAtPosition(position)
            }

            override fun onPageScrollStateChanged(i: Int) {

            }
        })
    }

    override fun initNet() {
    }
}