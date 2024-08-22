package com.kyawlinnthant.network

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val status: String,
    val type: String,
    val message: String,
)
