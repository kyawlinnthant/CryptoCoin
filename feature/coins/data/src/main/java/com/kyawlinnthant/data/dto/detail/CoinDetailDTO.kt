package com.kyawlinnthant.data.dto.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailDTO(
    @SerialName("data")
    val data: DetailData?,
    @SerialName("status")
    val status: String?,
)
