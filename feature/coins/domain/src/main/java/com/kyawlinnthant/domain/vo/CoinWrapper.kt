package com.kyawlinnthant.domain.vo

import com.kyawlinnthant.data.dto.coins.Coin
import com.kyawlinnthant.data.dto.detail.CoinDetail

fun Coin.toVo(): CoinVo {
    return CoinVo(
        btcPrice = btcPrice ?: "",
        change = change ?: "",
        coinRankingUrl = coinRankingUrl ?: "",
        color = color ?: "",
        contractAddresses =
            contractAddresses?.map {
                it ?: ""
            } ?: emptyList(),
        hVolume = hVolume ?: "",
        iconUrl = iconUrl ?: "",
        listedAt = listedAt ?: 0,
        lowVolume = lowVolume ?: false,
        marketCap = marketCap ?: "",
        name = name ?: "",
        price = price ?: "",
        rank = rank ?: 0,
        sparkline =
            sparkline?.map {
                it ?: ""
            } ?: emptyList(),
        symbol = symbol ?: "",
        tier = tier ?: 0,
        uuid = uuid ?: "",
    )
}

fun CoinDetail.toVo(): CoinDetailVo {
    return CoinDetailVo(
        image = this.iconUrl ?: "",
        name = this.name ?: "",
        symbol = this.symbol ?: "",
        color = this.color ?: "",
        price = this.price ?: "",
        marketCap = this.marketCap ?: "",
        description = this.description ?: "",
        website = this.websiteUrl ?: "",
        uuid = this.uuid ?: "",
    )
}
