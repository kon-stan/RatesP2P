package com.konstan.ratesp2p.mvvm.presentation.screens.p2p

import androidx.lifecycle.*
import com.konstan.ratesp2p.core.presentation.BaseViewModel
import com.konstan.ratesp2p.networking.models.p2p.Fiat
import com.konstan.ratesp2p.networking.models.p2p.Rate
import com.konstan.ratesp2p.networking.repository.p2p.P2PRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class P2PViewModel @Inject constructor(
    private val networkRepository: P2PRepository,
) : BaseViewModel() {

    val amount = MutableLiveData("")
    val rate = MutableLiveData<Rate?>()
    val errorText = MutableLiveData<String?>()
    @FlowPreview
    private val amountState: LiveData<String>
        get() = amount.asFlow().debounce(500).asLiveData(Dispatchers.Default)

    @FlowPreview
    fun startInit(lifecycleOwner: LifecycleOwner) {
        amountState.observe(lifecycleOwner, Observer {

            viewModelScope.launch {
                val rates = networkRepository.getRates(it?.toFloatOrNull(), Fiat.RUB, Fiat.KZT)
                if (rates === null && !it.isNullOrBlank()) {
                    errorText.postValue("Не найдено ордеров для ${amount.value}")
                }

                rate.postValue(rates)
            }
        })
    }
}