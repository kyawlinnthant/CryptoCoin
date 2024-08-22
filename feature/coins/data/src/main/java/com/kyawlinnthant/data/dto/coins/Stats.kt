package com.kyawlinnthant.data.dto.coins

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    @SerialName("total")
    val total: Int?,
    @SerialName("total24hVolume")
    val total24hVolume: String?,
    @SerialName("totalCoins")
    val totalCoins: Int?,
    @SerialName("totalExchanges")
    val totalExchanges: Int?,
    @SerialName("totalMarketCap")
    val totalMarketCap: String?,
    @SerialName("totalMarkets")
    val totalMarkets: Int?,
)
