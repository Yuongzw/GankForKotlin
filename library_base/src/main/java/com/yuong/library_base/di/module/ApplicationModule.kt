package com.yuong.library_base.di.module

import android.content.Context
import com.yuong.library_base.base.MyApplication
import com.yuong.library_base.di.scope.ContextLife
import com.yuong.library_base.di.scope.PerApp
import dagger.Module
import dagger.Provides


/**
 * ApplicationModule
 * Created yuong
 */
@Module
class ApplicationModule(private val mApplication: MyApplication) {

    @Provides
    @PerApp
    @ContextLife("Application")
    fun provideApplicationContext() :Context{
        return mApplication.applicationContext
    }

}