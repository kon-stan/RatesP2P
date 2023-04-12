package com.konstan.ratesp2p.mvvm.presentation.screens.repositoryDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.konstan.ratesp2p.core.utils.ViewModelFactory
import com.konstan.ratesp2p.mvvm.R
import com.konstan.ratesp2p.mvvm.databinding.FragmentP2pBinding
import com.konstan.ratesp2p.mvvm.presentation.screens.BaseFragment
import com.konstan.ratesp2p.mvvm.presentation.screens.p2p.P2PViewModel
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class P2PFragment:
    BaseFragment<P2PViewModel, FragmentP2pBinding>(R.layout.fragment_p2p) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override val viewModel by viewModels<P2PViewModel> { viewModelFactory }

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startInit(viewLifecycleOwner)
    }


}