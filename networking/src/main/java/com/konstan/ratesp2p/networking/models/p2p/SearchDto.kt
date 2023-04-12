package com.konstan.ratesp2p.networking.models.p2p

enum class Assets {
    USDT
}

enum class Fiat {
    KZT, RUB
}

enum class TradeType {
    BUY, SELL
}

enum class PayTypes (val color: String) {
    TINKOFF("TinkoffNew"), KASPI("KaspiBank")
}


data class SearchDto(
    var asset: Assets = Assets.USDT,
    var countries: List<String> = emptyList(),
    var fiat: Fiat,
    var merchantCheck: Boolean = false,
    var page: Number = 1,
    var payTypes: List<String> = emptyList(),
    var proMerchantAds: Boolean = false,
    var rows: Number = 10,
    var tradeType: TradeType,
    var transAmount: String? = null,
)
