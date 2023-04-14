package com.konstan.ratesp2p.mvvm.dagger

import com.konstan.ratesp2p.core.utils.AppScope
import com.konstan.ratesp2p.mvvm.App
import com.konstan.ratesp2p.networking.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@AppScope
@Component(modules = [
    AppModule::class,
    ActivitiesModule::class,
    NetworkModule::class,
])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}