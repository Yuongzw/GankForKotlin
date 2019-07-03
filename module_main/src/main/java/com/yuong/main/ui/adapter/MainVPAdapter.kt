package com.yuong.main.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.yuong.library_base.base.BaseFragment

class MainVPAdapter(fm: FragmentManager, private val fragments: List<BaseFragment<*, *>>) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //        super.destroyItem(container, position, object);
    }
}
