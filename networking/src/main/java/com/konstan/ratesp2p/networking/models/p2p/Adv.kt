package com.example.myapplication.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Adv(
    var price: String,
): Parcelable
