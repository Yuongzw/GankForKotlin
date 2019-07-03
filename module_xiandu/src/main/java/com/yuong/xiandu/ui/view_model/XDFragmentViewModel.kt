package com.yuong.xiandu.ui.view_model

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.yuong.library_base.base.BaseViewModel
import com.yuong.library_base.net.BaseLoadListener
import com.yuong.xiandu.R
import com.yuong.xiandu.bean.XianduCategoryBean
import com.yuong.xiandu.bean.XianduSubCategoryBean
import com.yuong.xiandu.databinding.FragmentXianduBinding
import com.yuong.xiandu.model.XianduModel
import com.yuong.xiandu.model.XianduModelImpl
import com.yuong.xiandu.ui.adapter.XDLeftAdapter
import com.yuong.xiandu.ui.adapter.XDRightAdapter

class XDFragmentViewModel: BaseViewModel<FragmentXianduBinding>(), BaseLoadListener<Any> {
    private lateinit var model: XianduModel
    private lateinit var leftAdapter: XDLeftAdapter
    private lateinit var rightAdapter:XDRightAdapter
    private lateinit var leftSkeletonScreen: SkeletonScreen
    private lateinit var rightSkeletonScreen: SkeletonScreen


    override fun initUi() {
        initRecyclerView()

    }

    private fun initRecyclerView() {
        leftAdapter = XDLeftAdapter(activity!!)
        binding!!.xianduRvLeft.layoutManager = LinearLayoutManager(activity)
        binding!!.xianduRvLeft.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding!!.xianduRvLeft.adapter = leftAdapter
        rightAdapter = XDRightAdapter(activity!!)
        binding!!.xianduRvRight.layoutManager = LinearLayoutManager(activity)
        binding!!.xianduRvRight.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding!!.xianduRvRight.adapter = rightAdapter

        //骨架屏
        leftSkeletonScreen = Skeleton.bind(binding!!.xianduRvLeft)
            .adapter(leftAdapter)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1200)
            .count(10)
            .load(R.layout.xiandu_category_item_skeleton)
            .show()

        //骨架屏
        rightSkeletonScreen = Skeleton.bind(binding!!.xianduRvRight)
            .adapter(rightAdapter)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1200)
            .count(10)
            .load(R.layout.xiandu_category_item_skeleton)
            .show()

        leftAdapter.onItemClickListener = object :XDLeftAdapter.OnItemClick {
            override fun onItemClickListener(en_name: String) {
                model.loadXianduSubCategory(en_name, this@XDFragmentViewModel)
            }
        }

    }

    override fun initNet() {
        model = XianduModelImpl(activity!!.applicationContext)
        model.loadXianduSuperCategory(this)
    }

    override fun loadSuccess(t: Any) {
        if (t is XianduCategoryBean) {
            leftAdapter.setNewData(t.results)
            leftSkeletonScreen.hide()
            model.loadXianduSubCategory(t.results!![0].en_name!!, this)
        } else if (t is XianduSubCategoryBean) {
            rightAdapter.setNewData(t.results)
            rightSkeletonScreen.hide()
        }
    }

    override fun loadFailure(message: String) {
    }

    override fun loadStart() {
    }

    override fun loadComplete() {
    }
}