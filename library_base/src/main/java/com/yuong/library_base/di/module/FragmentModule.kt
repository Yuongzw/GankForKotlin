package com.yuong.library_base.di.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import com.yuong.library_base.di.scope.ContextLife
import com.yuong.library_base.di.scope.PerActivity
import com.yuong.library_base.di.scope.PerFragment
import dagger.Module
import dagger.Provides

/**
 * FragmentModule
 * Created yuong
 */
@Module
class FragmentModule(private val mFragment: Fragment) {

    @Provides
    @PerFragment
    @ContextLife("Activity")
    fun provideActivityContext(): Context {
        return mFragment.activity!!
    }

    @Provides
    @PerFragment
    fun provideActivity(): Activity {
        return mFragment.activity!!
    }

    @Provides
    @PerFragment
    fun provideFragment(): Fragment {
        return mFragment
    }
}