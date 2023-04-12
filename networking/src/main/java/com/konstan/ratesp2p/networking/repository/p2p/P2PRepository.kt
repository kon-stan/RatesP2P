package com.konstan.ratesp2p.networking.repository.p2p

import com.konstan.ratesp2p.networking.models.p2p.Fiat
import com.konstan.ratesp2p.networking.models.p2p.Rate

interface P2PRepository {
    suspend fun getRates(amount: Float?, fiatToBuy: Fiat, fiatToSell: Fiat): Rate?
    
}