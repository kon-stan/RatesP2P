package com.konstan.ratesp2p.mvvm.dagger

import com.konstan.ratesp2p.core.utils.ActivityScope
import com.konstan.ratesp2p.mvvm.presentation.activity.MainActivity
import com.konstan.ratesp2p.mvvm.presentation.activity.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivitiesModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun contributeToMainActivity(): MainActivity
}