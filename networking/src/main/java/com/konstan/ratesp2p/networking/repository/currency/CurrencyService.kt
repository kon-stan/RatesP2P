package com.konstan.ratesp2p.networking.repository.currency

import com.konstan.ratesp2p.networking.XmlResponse
import com.konstan.ratesp2p.networking.models.currency.Currency
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyService {

    @GET("https://nationalbank.kz/rss/rates_all.xml")
    @XmlResponse
    fun getCurrencies(): Deferred<Response<Currency>>
}