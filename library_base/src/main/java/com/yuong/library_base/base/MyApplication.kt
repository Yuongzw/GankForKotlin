package com.yuong.library_base.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.matrix.Matrix
import com.yuong.library_base.di.component.ApplicationComponent
import com.yuong.library_base.di.component.DaggerApplicationComponent
import com.yuong.library_base.di.module.ApplicationModule
import com.yuong.library_base.utils.ToastUtils
import com.tencent.sqlitelint.SQLiteLintPlugin
import com.tencent.sqlitelint.config.SQLiteLintConfig
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.iocanary.config.IOConfig
import com.tencent.matrix.resource.ResourcePlugin
import com.tencent.matrix.resource.config.ResourceConfig
import com.tencent.matrix.trace.TracePlugin
import com.tencent.matrix.trace.config.TraceConfig
import com.yuong.library_base.TestPluginListener
import com.yuong.library_base.config.DynamicConfigImplDemo


class MyApplication : Application() {
    private var mApplicationComponent: ApplicationComponent? = null

    companion object {
        var instance: Application? = null
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this
        initArouter()
        initMatrix()
        initApplicationComponent()
        ToastUtils.init(context!!)
        MultiDex.install(this)
    }

    fun initMatrix(){
        //matrix
        val dynamicConfig = DynamicConfigImplDemo()
        val fpsEnable = dynamicConfig.isFPSEnable
        val traceEnable = dynamicConfig.isTraceEnable

        val builder = Matrix.Builder(instance)
        builder.patchListener(TestPluginListener(instance!!))

//trace
        val traceConfig = TraceConfig.Builder()
            .dynamicConfig(dynamicConfig)
            //按照自己需求开启以下监控任务
            .enableFPS(fpsEnable)
            .enableEvilMethodTrace(traceEnable)
            .enableAnrTrace(traceEnable)
            .enableStartup(traceEnable)
            //一定要写，改成自己项目中的splash页面即可，不然会奔溃
            .splashActivities("com.yuong.main.SplashActivity;")
            //debug模式
            .isDebug(true)
            //dev环境
            .isDevEnv(false)
            .build()

        val tracePlugin = TracePlugin(traceConfig)
        builder.plugin(tracePlugin)

//resource
        val resourceConfig = ResourceConfig.Builder()
            .dynamicConfig(dynamicConfig)
            //true获取hprof文件，否则没法分析哪里内存泄露了
            .setDumpHprof(true)
            //只有官方demo中才会为true
            .setDetectDebuger(false)
            .build()

        val resourcePlugin = ResourcePlugin(resourceConfig)
        builder.plugin(resourcePlugin)
// 当activity destroy之后，自动断开从引用的view到gc root之间的路径
        ResourcePlugin.activityLeakFixer(this)

//io
        val ioConfig = IOConfig.Builder()
            .dynamicConfig(dynamicConfig)
            .build()
        val ioCanaryPlugin = IOCanaryPlugin(ioConfig)
        builder.plugin(ioCanaryPlugin)

        Matrix.init(builder.build())
        tracePlugin.start()
        ioCanaryPlugin.start()
        resourcePlugin.start()
    }

    private fun initArouter() {
        if (isDebug(instance!!)) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(instance) // 尽可能早，推荐在Application中初始化
    }

    fun isDebug(context: Context): Boolean {
        return context.applicationInfo != null && context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }


    private fun initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return mApplicationComponent!!
    }
}