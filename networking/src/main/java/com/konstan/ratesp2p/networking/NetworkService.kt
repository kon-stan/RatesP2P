package com.konstan.ratesp2p.networking

import com.konstan.ratesp2p.networking.models.p2p.ResponseBinanceP2p
import com.konstan.ratesp2p.networking.models.p2p.SearchDto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {

    @POST("/bapi/c2c/v2/friendly/c2c/adv/search")
    fun getP2P(@Body dto: SearchDto): Deferred<Response<ResponseBinanceP2p>>
}