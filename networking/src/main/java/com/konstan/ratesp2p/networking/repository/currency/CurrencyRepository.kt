package com.konstan.ratesp2p.networking.repository.currency

import com.konstan.ratesp2p.networking.models.p2p.Fiat
import com.konstan.ratesp2p.networking.models.p2p.Rate

interface CurrencyRepository {
    suspend fun getCurrencyKZT(amount: Float?, fiat: Fiat): Float?
    
}