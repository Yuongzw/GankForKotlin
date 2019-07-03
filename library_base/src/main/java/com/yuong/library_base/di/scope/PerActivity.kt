package com.yuong.library_base.di.scope

import javax.inject.Scope

/**
 * 自定义 Activity的Scope
 * Created yuong
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity {
}