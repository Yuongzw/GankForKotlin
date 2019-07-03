package com.yuong.library_base.di.scope

import javax.inject.Qualifier


/**
 * 自定义scope  默认是值为：application
 * Created by yuong
 */
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ContextLife(val value: String = "Application")