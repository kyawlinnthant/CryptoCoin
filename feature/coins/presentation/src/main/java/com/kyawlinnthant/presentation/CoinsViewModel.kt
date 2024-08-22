package com.kyawlinnthant.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.kyawlinnthant.domain.usecase.GetCoins
import com.kyawlinnthant.domain.usecase.GetDetail
import com.kyawlinnthant.domain.usecase.RefreshCoins
import com.kyawlinnthant.domain.usecase.SearchCoins
import com.kyawlinnthant.domain.vo.CoinUiModel
import com.kyawlinnthant.domain.vo.CoinVo
import com.kyawlinnthant.domain.vo.RequestState
import com.kyawlinnthant.presentation.state.CoinsAction
import com.kyawlinnthant.presentation.state.CoinsEvent
import com.kyawlinnthant.presentation.state.CoinsViewModelState
import com.kyawlinnthant.presentation.state.RefreshEvent
import com.kyawlinnthant.util.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class CoinsViewModel
    @Inject
    constructor(
        private val getCoins: GetCoins,
        private val searchCoins: SearchCoins,
        private val getDetail: GetDetail,
        private val refreshCoins: RefreshCoins,
    ) : ViewModel() {
        private val vmCoinEvent = MutableSharedFlow<CoinsEvent>()
        val coinEvent: SharedFlow<CoinsEvent> get() = vmCoinEvent

        private val vmRefreshEvent = MutableSharedFlow<RefreshEvent>()
        val refreshEvent: SharedFlow<RefreshEvent> get() = vmRefreshEvent

        private val vmState = MutableStateFlow(CoinsViewModelState())

        val coinsState =
            vmState.map(CoinsViewModelState::asCoinState).stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = vmState.value.asCoinState(),
            )
        val searchedState =
            vmState.map(CoinsViewModelState::asSearchState).stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = vmState.value.asSearchState(),
            )
        val detailState =
            vmState.map(CoinsViewModelState::asDetailState).stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = vmState.value.asDetailState(),
            )
        val searchWidget =
            vmState.map(CoinsViewModelState::asSearchWidget).stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = vmState.value.asSearchWidget(),
            )

        init {
            fetchCoins()
            startPeriodicRefresh()
        }

        private var timer: Job? = null

        private fun startPeriodicRefresh() {
            timer?.cancel() // restart the timer
            timer =
                viewModelScope.launch {
                    while (true) {
                        delay(10.seconds)
                        vmRefreshEvent.emit(RefreshEvent.RefreshTimerReach)
                    }
                }
        }

        fun onAction(action: CoinsAction) {
            when (action) {
                is CoinsAction.OnPullRefresh -> updateRefreshing(action.refreshing)
                is CoinsAction.UpdateKeyword -> updateKeyword(action.keyword)
                is CoinsAction.OnDetailClick -> updateDetailId(action.uuid)
                CoinsAction.OnDismissSheet -> updateBottomSheetState(shouldShow = false)
                CoinsAction.OnRetryDetail -> getCoinDetail()
                is CoinsAction.RefreshCoins -> refreshPage(action.size)
                CoinsAction.RestartTimer -> startPeriodicRefresh()
            }
        }

        private fun refreshPage(size: Int) {
            viewModelScope.launch {
                when (val response = refreshCoins(size = size)) {
                    is DataResult.Failed -> {
                        vmCoinEvent.emit(CoinsEvent.SnackMessage(message = response.message))
                    }

                    is DataResult.Success -> {
                        vmCoinEvent.emit(CoinsEvent.RefreshCoinsSuccess)
                        val updatedCoinsFlow =
                            updateCoinUiModel(vmState.value.coinState.coins, response.data)
                        vmState.update { state ->
                            state.copy(
                                coinState =
                                    state.coinState.copy(
                                        coins = updatedCoinsFlow.cachedIn(this),
                                        refreshing = false,
                                    ),
                            )
                        }
                    }
                }
            }
        }

        private fun updateCoinUiModel(
            originalPagingData: Flow<PagingData<CoinUiModel>>,
            newCoinVoList: List<CoinVo>,
        ): Flow<PagingData<CoinUiModel>> {
            return originalPagingData.map { pagingData ->
                pagingData.map { coinUiModel ->
                    when (coinUiModel) {
                        is CoinUiModel.CoinsItem -> {
                            val updatedCoinVo = newCoinVoList.find { it.rank == coinUiModel.coin.rank }
                            if (updatedCoinVo != null) {
                                CoinUiModel.CoinsItem(updatedCoinVo)
                            } else {
                                coinUiModel
                            }
                        }

                        is CoinUiModel.InviteFriendItem -> coinUiModel
                    }
                }
            }
        }

        private fun updateDetailId(uuid: String) {
            updateBottomSheetState(shouldShow = true)
            if (uuid.trim().isNotEmpty() && uuid.trim() != vmState.value.detailState.uuid) {
                vmState.update { state ->
                    state.copy(
                        detailState =
                            state.detailState.copy(
                                uuid = uuid,
                            ),
                    )
                }
                getCoinDetail(uuid)
            }
        }

        private fun getCoinDetail(uuid: String = vmState.value.detailState.uuid.trim()) {
            viewModelScope.launch {
                vmState.update { state ->
                    state.copy(
                        detailState =
                            state.detailState.copy(
                                data = RequestState.Loading,
                            ),
                    )
                }
                getDetail(uuid).apply {
                    vmState.update { state ->
                        state.copy(
                            detailState =
                                state.detailState.copy(
                                    data = this,
                                ),
                        )
                    }
                }
            }
        }

        private fun updateBottomSheetState(shouldShow: Boolean) {
            vmState.update { state ->
                state.copy(
                    detailState =
                        state.detailState.copy(
                            shouldShowSheet = shouldShow,
                        ),
                )
            }
        }

        private fun updateKeyword(keyword: String) {
            vmState.update { state ->
                state.copy(
                    searchState =
                        state.searchState.copy(
                            keyword = keyword,
                        ),
                )
            }
            if (keyword.trim().isNotEmpty()) {
                search(keyword = keyword)
            }
        }

        private fun updateRefreshing(isRefresh: Boolean) {
            if (isRefresh) fetchCoins()
            vmState.update { state ->
                state.copy(
                    coinState =
                        state.coinState.copy(
                            refreshing = isRefresh,
                        ),
                )
            }
        }

        private fun fetchCoins() {
            viewModelScope.launch {
                getCoins().collectLatest {
                    vmState.update { state ->
                        state.copy(
                            coinState =
                                state.coinState.copy(
                                    coins =
                                        flow {
                                            emit(it)
                                        }.cachedIn(this),
                                    refreshing = false,
                                ),
                        )
                    }
                }
            }
        }

        private var searchJob: Job? = null

        private fun search(keyword: String = vmState.value.searchState.keyword.trim()) {
            searchJob?.cancel()
            vmState.update { state ->
                state.copy(
                    searchState =
                        state.searchState.copy(
                            coins = emptyFlow(),
                        ),
                )
            }
            searchJob =
                viewModelScope.launch {
                    delay(0.5.seconds)
                    searchCoins(keyword).collectLatest {
                        vmState.update { state ->
                            state.copy(
                                searchState =
                                    state.searchState.copy(
                                        coins =
                                            flow {
                                                emit(it)
                                            }.cachedIn(this),
                                    ),
                            )
                        }
                    }
                }
        }
    }
