package com.konstan.ratesp2p.networking.repository.currency

import com.konstan.ratesp2p.networking.models.p2p.*
import javax.inject.Inject
import javax.net.ssl.SSLProtocolException

class CurrencyRepositoryImpl @Inject constructor(
    private val networkService: CurrencyService
): CurrencyRepository {

    override suspend fun getCurrencyKZT(amount: Float?, fiat: Fiat): Float? {
        if (amount === null)  return null

        try {
            val currencies = networkService.getCurrencies().await()

            if (!currencies.isSuccessful) {
                throw Exception(currencies.errorBody()?.string())
            }

            val currencyFiat = currencies.body()?.channel?.item?.find { i -> i.title == fiat.toString() }?.description

            if (currencyFiat === null) return null;

            return amount / currencyFiat
        } catch (e: SSLProtocolException) {
            return getCurrencyKZT(amount, fiat)
        }


    }
}