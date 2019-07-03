package com.yuong.home

import com.yuong.home.ui.fragment.HomeFragment
import com.yuong.library_base.di.component.ApplicationComponent
import com.yuong.library_base.di.component.FragmentComponent
import com.yuong.library_base.di.module.FragmentModule
import com.yuong.library_base.di.scope.PerFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface HomeFragmentComponent: FragmentComponent {
    fun inject(fragment: HomeFragment)
}