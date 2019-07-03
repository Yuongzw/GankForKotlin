package com.yuong.category.ui.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.yuong.category.ui.fragment.CategoryDetailFragment

class CategoryPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private var titles = listOf("all", "Android", "iOS", "休息视频", "拓展资源", "前端", "瞎推荐", "App")

    override fun getItem(position: Int): Fragment {
        val fragment = CategoryDetailFragment()
        val bundle = Bundle()
        bundle.putString("type", titles[position])
        fragment.setArguments(bundle)
        return fragment
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0) {
            return "全部"
        }
        return  titles[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, `object`)
    }
}