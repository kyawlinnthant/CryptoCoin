package com.kyawlinnthant.data.dto.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Supply(
    @SerialName("circulating")
    val circulating: String? = "",
    @SerialName("confirmed")
    val confirmed: Boolean? = false,
    @SerialName("max")
    val max: String? = "",
    @SerialName("supplyAt")
    val supplyAt: Int? = 0,
    @SerialName("total")
    val total: String? = "",
)
