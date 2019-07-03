package com.yuong.library_base.di.component

import android.app.Activity
import android.content.Context
import com.yuong.library_base.di.module.FragmentModule
import com.yuong.library_base.di.scope.ContextLife
import com.yuong.library_base.di.scope.PerFragment
import dagger.Component

/**
 * FragmentComponent  提供Fragment注入
 * Created by yuong
 * 使用的是Dagger2的方法和参数
 */
@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    @ContextLife("Activity")
    fun getActivityContext(): Context

    @ContextLife("Application")
    fun getApplicationContext(): Context

    fun getActivity(): Activity
}