package com.kyawlinnthant.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kyawlinnthant.coins.presentation.R
import com.kyawlinnthant.presentation.coins.CoinsContent
import com.kyawlinnthant.presentation.item.DetailSheet
import com.kyawlinnthant.presentation.item.ErrorItem
import com.kyawlinnthant.presentation.item.LoadingItem
import com.kyawlinnthant.presentation.item.ResultDisplayView
import com.kyawlinnthant.presentation.item.SearchTopBar
import com.kyawlinnthant.presentation.search.SearchContent
import com.kyawlinnthant.presentation.state.CoinsAction
import com.kyawlinnthant.presentation.state.CoinsEvent
import com.kyawlinnthant.presentation.state.SearchWidget
import com.kyawlinnthant.theme.dimen
import com.kyawlinnthant.theme.rememberWindowSize
import com.kyawlinnthant.util.CoinDisplayFormatter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinsScreen() {
    val viewModel: CoinsViewModel = hiltViewModel()
    val searchWidget = viewModel.searchWidget.collectAsState()
    val coinsState = viewModel.coinsState.collectAsState()
    val searchState = viewModel.searchedState.collectAsState()
    val detailState = viewModel.detailState.collectAsState()
    val snackState: SnackbarHostState = remember { SnackbarHostState() }
    val uriHandler = LocalUriHandler.current
    val scope = rememberCoroutineScope()
    val window = rememberWindowSize()
    val refreshSuccess = stringResource(id = R.string.success_refresh)

    LaunchedEffect(true) {
        viewModel.coinEvent.collectLatest {
            when (it) {
                CoinsEvent.RefreshCoinsSuccess -> snackState.showSnackbar(refreshSuccess)
                is CoinsEvent.SnackMessage -> snackState.showSnackbar(message = it.message)
            }
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            SearchTopBar(
                searchQuery = searchState.value.keyword,
                onTextChanged = {
                    viewModel.onAction(CoinsAction.UpdateKeyword(it))
                },
                onClearQueryClicked = {
                    viewModel.onAction(CoinsAction.UpdateKeyword(""))
                },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackState, modifier = Modifier.navigationBarsPadding())
        },
    ) { paddingValues ->

        if (detailState.value.shouldShowSheet) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.onAction(CoinsAction.OnDismissSheet) },
                content = {
                    ResultDisplayView(
                        onLoading = {
                            Box(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = MaterialTheme.dimen.base6x)
                                        .navigationBarsPadding(),
                                contentAlignment = Alignment.Center,
                            ) {
                                LoadingItem()
                            }
                        },
                        onSuccess = { vo ->
                            DetailSheet(coinDetail = vo) {
                                scope.launch {
                                    uriHandler.openUri(it)
                                }
                            }
                        },
                        onError = {
                            Box(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = MaterialTheme.dimen.base6x)
                                        .navigationBarsPadding(),
                                contentAlignment = Alignment.Center,
                            ) {
                                ErrorItem(message = it) {
                                    viewModel.onAction(CoinsAction.OnRetryDetail)
                                }
                            }
                        },
                        requestState = detailState.value.data,
                    )
                },
                windowInsets = WindowInsets(0, 0, 0, 0),
                sheetState = rememberModalBottomSheetState(),
            )
        }

        when (searchWidget.value) {
            SearchWidget.OPENED ->
                SearchContent(
                    searchCoins = searchState.value.coins.collectAsLazyPagingItems(),
                    paddingValues = paddingValues,
                    onItemClick = {
                        viewModel.onAction(CoinsAction.OnDetailClick(uuid = it))
                    },
                    onInviteClick = {
                        scope.launch {
                            uriHandler.openUri(CoinDisplayFormatter.INVITE_URL)
                        }
                    },
                    windowWidth = window.width,
                )

            SearchWidget.CLOSED ->
                CoinsContent(
                    coins = coinsState.value.coins.collectAsLazyPagingItems(),
                    paddingValues = paddingValues,
                    onItemClick = {
                        viewModel.onAction(CoinsAction.OnDetailClick(uuid = it))
                    },
                    onRefresh = {
                        viewModel.onAction(CoinsAction.OnPullRefresh(true))
                    },
                    isRefreshing = coinsState.value.refreshing,
                    onInviteClick = {
                        scope.launch {
                            uriHandler.openUri(CoinDisplayFormatter.INVITE_URL)
                        }
                    },
                    onRefreshAll = {
                        viewModel.onAction(CoinsAction.RefreshCoins(it))
                    },
                    onRestartTimer = {
                        viewModel.onAction(CoinsAction.RestartTimer)
                    },
                    windowWidth = window.width,
                    refreshEvent = viewModel.refreshEvent,
                )
        }
    }
}
