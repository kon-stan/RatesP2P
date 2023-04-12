package com.konstan.ratesp2p.networking.repository.p2p

import com.konstan.ratesp2p.networking.NetworkService
import com.konstan.ratesp2p.networking.models.p2p.*
import javax.inject.Inject

class P2PRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
): P2PRepository {

    override suspend fun getRates(amount: Float?, fiatToBuy: Fiat, fiatToSell: Fiat): Rate? {
        if (amount === null)  return null

        val buySearch = SearchDto(fiat = fiatToBuy, tradeType = TradeType.BUY, transAmount = amount.toInt().toString(), payTypes = listOf(PayTypes.TINKOFF.color))
        val buyResponse = networkService.getP2P(buySearch).await()

        if (!buyResponse.isSuccessful)
            throw Exception(buyResponse.errorBody()?.string())

        val buyPrice = buyResponse.body()?.data?.getOrNull(0)?.adv?.price?.toFloatOrNull() ?: return null

        val canBuyAmount = amount / buyPrice
        val sellSearch = SearchDto(fiat = fiatToSell, tradeType = TradeType.SELL, payTypes = listOf(PayTypes.KASPI.color))
        val sellResponse = networkService.getP2P(sellSearch).await()
        val sellPrice = sellResponse.body()?.data?.getOrNull(0)?.adv?.price?.toFloatOrNull() ?: return null
        val canSellAmount = canBuyAmount * sellPrice

        return Rate(
            Assets.USDT,
            assetsPrice = listOf(
                AssetsPrice(fiatToBuy, buyPrice, canBuyAmount, true),
                AssetsPrice(fiatToSell, sellPrice, canSellAmount, false ))
        )
    }
}