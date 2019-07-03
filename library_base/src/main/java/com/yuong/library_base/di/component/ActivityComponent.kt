package com.yuong.library_base.di.component

import android.app.Activity
import android.content.Context
import com.yuong.library_base.di.module.ActivityModule
import com.yuong.library_base.di.scope.ContextLife
import com.yuong.library_base.di.scope.PerActivity
import dagger.Component

/**
 * ActivityComponent
 * @author yuong
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    @ContextLife("Activity")
    fun getActivityContext(): Context

    @ContextLife("Application")
    fun getApplicationContext(): Context

    fun getActivity(): Activity

}