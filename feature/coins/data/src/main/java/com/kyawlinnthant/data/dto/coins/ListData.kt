package com.kyawlinnthant.data.dto.coins

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListData(
    @SerialName("coins")
    val coins: List<Coin>?,
    @SerialName("stats")
    val stats: Stats?,
)
