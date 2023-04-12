package com.konstan.ratesp2p.mvvm

import com.konstan.ratesp2p.mvvm.dagger.AppComponent
import com.konstan.ratesp2p.mvvm.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        initDagger()
        super.onCreate()
        appComponent.inject(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}