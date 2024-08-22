package com.kyawlinnthant.data.dto.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllTimeHigh(
    @SerialName("price")
    val price: String? = "",
    @SerialName("timestamp")
    val timestamp: Int? = 0,
)
