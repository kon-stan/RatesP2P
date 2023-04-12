package com.konstan.ratesp2p.networking.models.p2p

import android.os.Parcelable
import com.example.myapplication.models.BinanceP2PData
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseBinanceP2p(
   var code: String,
   var data: List<BinanceP2PData>,
): Parcelable