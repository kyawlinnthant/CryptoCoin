package com.kyawlinnthant.data.dto.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailData(
    @SerialName("coin")
    val coin: CoinDetail?,
)
