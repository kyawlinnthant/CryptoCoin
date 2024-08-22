package com.kyawlinnthant.presentation.state

import androidx.paging.PagingData
import com.kyawlinnthant.domain.vo.CoinDetailVo
import com.kyawlinnthant.domain.vo.CoinUiModel
import com.kyawlinnthant.domain.vo.CoinVo
import com.kyawlinnthant.domain.vo.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CoinsViewModelState(
    val coinState: CoinsState = CoinsState(),
    val searchState: SearchState = SearchState(),
    val detailState: DetailState = DetailState(),
    val searchWidget: SearchWidget = SearchWidget.CLOSED,
) {
    fun asCoinState() = coinState

    fun asSearchState() = searchState

    fun asDetailState() = detailState

    fun asSearchWidget() =
        if (searchState.keyword.trim().isEmpty()) {
            SearchWidget.CLOSED
        } else {
            SearchWidget.OPENED
        }
}

data class CoinsState(
    val coins: Flow<PagingData<CoinUiModel>> = emptyFlow(),
    val refreshing: Boolean = false,
)

data class SearchState(
    val coins: Flow<PagingData<CoinVo>> = emptyFlow(),
    val keyword: String = "",
)

data class DetailState(
    val data: RequestState<CoinDetailVo> = RequestState.Idle,
    val uuid: String = "",
    val shouldShowSheet: Boolean = false,
)

enum class SearchWidget {
    OPENED,
    CLOSED,
}
