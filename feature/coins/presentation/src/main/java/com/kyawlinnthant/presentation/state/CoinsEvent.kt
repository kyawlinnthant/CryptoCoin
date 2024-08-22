package com.kyawlinnthant.presentation.state

sealed interface CoinsEvent {
    data object RefreshCoinsSuccess : CoinsEvent

    data class SnackMessage(val message: String) : CoinsEvent
}
