package com.yuong.library_base.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yuong.library_base.di.component.DaggerFragmentComponent
import com.yuong.library_base.di.component.FragmentComponent
import com.yuong.library_base.di.module.FragmentModule

abstract class BaseFragment<V: ViewDataBinding, VM: BaseViewModel<V>>: Fragment()  {
    lateinit var binding: V
    lateinit var viewModel: VM
    var isUIVisible: Boolean = false
    var isViewCreated: Boolean = false
    var isFirstLoad: Boolean = false
    protected var mFragmentComponent: FragmentComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFragmentComponent = initFragmentComponent()
        initInjector()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind<V>(view)!!
        viewModel = TUtil.getT<VM>(this, 1)!!
        viewModel.init(activity!!, binding, context!!)
        isViewCreated = true
        if (!isFirstLoad) {
            initView()
            lazyLoad()
            isFirstLoad = true
        }
    }

    private fun lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData()
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false
            isUIVisible = false
        }
    }

    //临时用这个代替
    override fun onResume() {
        super.onResume()
        isUIVisible = true
        lazyLoad()
    }


    //该方法调用希望在一个pager里，因此 FragmentPagerAdapter 可以使用这个，
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true
            lazyLoad()
        } else {
            isUIVisible = false
        }
    }

    /**
     * 初始化FragmentComponent
     */
    protected abstract fun initFragmentComponent():FragmentComponent?

    protected abstract fun initInjector()
//    protected open fun initInjector(){}

    abstract fun initView()

    abstract fun loadData()

    abstract fun getLayoutResource(): Int

    private fun tag(): String? {
        return this.javaClass.simpleName
    }

    protected fun toast(text: String) {
        android.widget.Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mFragmentComponent = null
    }

}