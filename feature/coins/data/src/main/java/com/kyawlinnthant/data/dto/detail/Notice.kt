package com.kyawlinnthant.data.dto.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Notice(
    @SerialName("type")
    val type: String?,
    @SerialName("value")
    val value: String?,
)
