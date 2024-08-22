package com.kyawlinnthant.presentation.state

sealed interface RefreshEvent {
    data object RefreshTimerReach : RefreshEvent
}
