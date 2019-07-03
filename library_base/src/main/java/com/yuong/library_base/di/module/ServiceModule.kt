package com.yuong.library_base.di.module

import android.app.Service
import android.content.Context
import com.yuong.library_base.di.scope.ContextLife
import com.yuong.library_base.di.scope.PerService
import dagger.Module
import dagger.Provides

/**
 * ServiceModule
 * Created yuong
 */
@Module
class ServiceModule(private val mService: Service) {

    @Provides
    @PerService
    @ContextLife("Service")
    fun ProvideServiceContext(): Context {
        return mService
    }
}
