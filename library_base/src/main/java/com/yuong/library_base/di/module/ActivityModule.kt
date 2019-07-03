package com.yuong.library_base.di.module

import android.app.Activity
import android.content.Context
import com.yuong.library_base.di.scope.ContextLife
import com.yuong.library_base.di.scope.PerActivity
import dagger.Module
import dagger.Provides

/**
 * ActivityModule
 * Created yuong
 */
@Module
class ActivityModule(private val mActivity: Activity) {


    @Provides
    @PerActivity
    @ContextLife("Activity")
    fun provideActivityContext():Context{
        return mActivity
    }

    @Provides
    @PerActivity
    fun provideActivity(): Activity {
        return mActivity
    }

}

