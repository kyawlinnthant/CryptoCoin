package com.kyawlinnthant.data.dto.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetail(
    @SerialName("allTimeHigh")
    val allTimeHigh: AllTimeHigh? = AllTimeHigh(),
    @SerialName("btcPrice")
    val btcPrice: String? = "",
    @SerialName("change")
    val change: String? = "",
    @SerialName("coinrankingUrl")
    val coinRankingUrl: String? = "",
    @SerialName("color")
    val color: String? = "",
    @SerialName("contractAddresses")
    val contractAddresses: List<String?>? = emptyList(),
    @SerialName("description")
    val description: String? = "",
    @SerialName("fullyDilutedMarketCap")
    val fullyDilutedMarketCap: String? = "",
    @SerialName("24hVolume")
    val hVolume: String? = "",
    @SerialName("iconUrl")
    val iconUrl: String? = "",
    @SerialName("links")
    val links: List<Link?>? = emptyList(),
    @SerialName("listedAt")
    val listedAt: Int? = 0,
    @SerialName("lowVolume")
    val lowVolume: Boolean? = false,
    @SerialName("marketCap")
    val marketCap: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("notices")
    val notices: List<Notice?>? = emptyList(),
    @SerialName("numberOfExchanges")
    val numberOfExchanges: Int? = 0,
    @SerialName("numberOfMarkets")
    val numberOfMarkets: Int? = 0,
    @SerialName("price")
    val price: String? = "",
    @SerialName("priceAt")
    val priceAt: Int? = 0,
    @SerialName("rank")
    val rank: Int? = 0,
    @SerialName("sparkline")
    val sparkline: List<String?>? = emptyList(),
    @SerialName("supply")
    val supply: Supply? = Supply(),
    @SerialName("symbol")
    val symbol: String? = "",
    @SerialName("tags")
    val tags: List<String?>? = emptyList(),
    @SerialName("uuid")
    val uuid: String? = "",
    @SerialName("websiteUrl")
    val websiteUrl: String? = "",
)
