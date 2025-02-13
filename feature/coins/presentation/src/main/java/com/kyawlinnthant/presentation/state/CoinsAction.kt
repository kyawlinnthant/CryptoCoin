package com.kyawlinnthant.presentation.state

sealed interface CoinsAction {
    data class UpdateKeyword(val keyword: String) : CoinsAction

    data class RefreshCoins(val size: Int) : CoinsAction

    data class OnDetailClick(val uuid: String) : CoinsAction

    data object OnDismissSheet : CoinsAction

    data class OnPullRefresh(val refreshing: Boolean) : CoinsAction

    data object OnRetryDetail : CoinsAction
}
