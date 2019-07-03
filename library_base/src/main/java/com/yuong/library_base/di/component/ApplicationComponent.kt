package com.yuong.library_base.di.component

import android.content.Context
import com.yuong.library_base.di.module.ApplicationModule
import com.yuong.library_base.di.scope.ContextLife
import com.yuong.library_base.di.scope.PerApp
import dagger.Component

/**
 *ApplicationComponent
 * @author yuong
 */
@PerApp
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @ContextLife("Application")
    fun getApplication():Context
}