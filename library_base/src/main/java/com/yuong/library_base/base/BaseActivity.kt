package com.yuong.library_base.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.yuong.library_base.di.component.ActivityComponent
import com.yuong.library_base.di.component.DaggerActivityComponent
import com.yuong.library_base.di.module.ActivityModule

abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<V>> : AppCompatActivity() {
    protected var binding: V? = null
    protected var viewModel: VM? = null
    protected var mActivityComponent: ActivityComponent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        //状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
            //            getWindow().setNavigationBarColor(Color.BLACK);
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResource())
        viewModel = TUtil.getT(this, 1)!!
        viewModel!!.init(this, binding!!, this.baseContext)
        initActivityComponent()
        initData()
    }

    abstract fun getLayoutResource(): Int

    abstract fun initData()

    /**
     * 初始化ActivityComponent
     */
    private fun initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).getApplicationComponent())
            .activityModule(ActivityModule(this))
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel!!.destroy()
        mActivityComponent = null
        FixMemLeak.fixLeak(this)
    }
}