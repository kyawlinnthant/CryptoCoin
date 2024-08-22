package com.kyawlinnthant.data.dto.coins

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coin(
    @SerialName("btcPrice")
    val btcPrice: String?,
    @SerialName("change")
    val change: String?,
    @SerialName("coinrankingUrl")
    val coinRankingUrl: String?,
    @SerialName("color")
    val color: String?,
    @SerialName("contractAddresses")
    val contractAddresses: List<String?>?,
    @SerialName("24hVolume")
    val hVolume: String?,
    @SerialName("iconUrl")
    val iconUrl: String?,
    @SerialName("listedAt")
    val listedAt: Int?,
    @SerialName("lowVolume")
    val lowVolume: Boolean?,
    @SerialName("marketCap")
    val marketCap: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("price")
    val price: String?,
    @SerialName("rank")
    val rank: Int?,
    @SerialName("sparkline")
    val sparkline: List<String?>?,
    @SerialName("symbol")
    val symbol: String?,
    @SerialName("tier")
    val tier: Int?,
    @SerialName("uuid")
    val uuid: String?,
)
