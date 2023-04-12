package com.konstan.ratesp2p.networking.models.p2p


data class AssetsPrice (
    val fiat: Fiat,
    val price: Float,
    val amount: Float,
    val isBuy: Boolean,
)

data class Rate(
    val assets: Assets,
    val assetsPrice: List<AssetsPrice>,
)
