package com.kyawlinnthant.domain.vo

data class CoinVo(
    val btcPrice: String = "",
    val change: String = "",
    val coinRankingUrl: String = "",
    val color: String = "",
    val contractAddresses: List<String> = emptyList(),
    val hVolume: String = "",
    val iconUrl: String = "",
    val listedAt: Int = 0,
    val lowVolume: Boolean = false,
    val marketCap: String = "",
    val name: String = "",
    val price: String = "",
    val rank: Int = 0,
    val sparkline: List<String> = emptyList(),
    val symbol: String = "",
    val tier: Int = 0,
    val uuid: String = "",
)
