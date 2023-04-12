package com.example.myapplication.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BinanceP2PData(
    var adv: Adv
): Parcelable