package com.kyawlinnthant.data.dto.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Link(
    @SerialName("name")
    val name: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("url")
    val url: String?,
)
