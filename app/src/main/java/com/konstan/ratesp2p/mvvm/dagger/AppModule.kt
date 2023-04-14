package com.konstan.ratesp2p.mvvm.dagger

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.konstan.ratesp2p.core.utils.ApplicationContext
import com.konstan.ratesp2p.core.utils.ViewModelFactory
import com.konstan.ratesp2p.mvvm.App
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier

@Module
interface AppModule {
    @Binds
    @ApplicationContext
    fun bindApplicationContext(application: App): Context

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}