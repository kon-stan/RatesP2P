package com.konstan.ratesp2p.mvvm.presentation.activity

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import com.konstan.ratesp2p.core.utils.ActivityScope
import com.konstan.ratesp2p.core.utils.FragmentScope
import com.konstan.ratesp2p.core.utils.ViewModelKey
import com.konstan.ratesp2p.core.utils.getSystemService
import com.konstan.ratesp2p.mvvm.presentation.screens.p2p.P2PModule
import com.konstan.ratesp2p.mvvm.presentation.screens.repositoryDetails.P2PFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
interface MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMedProfileViewModel(viewModel: MainViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [P2PModule::class])
    fun contributeP2PModuleFragment(): P2PFragment

    companion object {
        @Provides
        @ActivityScope
        fun provideInputManager(activity: MainActivity): InputMethodManager {
            return activity.getSystemService<InputMethodManager>(Context.INPUT_METHOD_SERVICE)
        }

        @Provides
        @ActivityScope
        fun provideCicerone() = Cicerone.create(Router())

        @Provides
        @ActivityScope
        fun provideRouter(cicerone: Cicerone<Router>) = cicerone.router

        @Provides
        @ActivityScope
        fun provideNavigatorHolder(cicerone: Cicerone<Router>) = cicerone.navigatorHolder
    }
}