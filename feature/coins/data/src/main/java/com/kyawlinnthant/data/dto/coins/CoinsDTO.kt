package com.kyawlinnthant.data.dto.coins

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinsDTO(
    @SerialName("data")
    val data: ListData?,
    @SerialName("status")
    val status: String?,
)
