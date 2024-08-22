package com.kyawlinnthant.domain.vo

sealed interface CoinUiModel {
    data object InviteFriendItem : CoinUiModel

    data class CoinsItem(val coin: CoinVo) : CoinUiModel
}
