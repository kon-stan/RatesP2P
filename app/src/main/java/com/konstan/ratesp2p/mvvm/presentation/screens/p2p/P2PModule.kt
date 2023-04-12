package com.konstan.ratesp2p.mvvm.presentation.screens.p2p

import androidx.lifecycle.ViewModel
import com.konstan.ratesp2p.core.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface P2PModule {
    @Binds
    @IntoMap
    @ViewModelKey(P2PViewModel::class)
    fun bindViewModel(viewModel: P2PViewModel): ViewModel
}