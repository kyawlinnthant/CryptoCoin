package com.kyawlinnthant.presentation.coins

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.kyawlinnthant.coins.presentation.R
import com.kyawlinnthant.domain.vo.CoinUiModel
import com.kyawlinnthant.domain.vo.Top3Rank
import com.kyawlinnthant.presentation.item.CoinItem
import com.kyawlinnthant.presentation.item.ErrorItem
import com.kyawlinnthant.presentation.item.InviteFriendItem
import com.kyawlinnthant.presentation.item.LoadingItem
import com.kyawlinnthant.presentation.item.Top3Header
import com.kyawlinnthant.theme.WindowType
import com.kyawlinnthant.theme.dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinsContent(
    coins: LazyPagingItems<CoinUiModel>,
    paddingValues: PaddingValues,
    isRefreshing: Boolean,
    windowWidth: WindowType,
    onRefresh: () -> Unit,
    onItemClick: (String) -> Unit,
    onInviteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp

    coins.apply {
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            modifier =
                modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
                contentPadding = PaddingValues(MaterialTheme.dimen.base2x),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.base),
            ) {
                item {
                    if (this@apply.itemCount != 0) {
                        val topRank =
                            this@apply.itemSnapshotList.items.take(3).map {
                                (it as CoinUiModel.CoinsItem).coin
                            }
                        val (top1, top2, top3) = topRank
                        val top3Rank =
                            Top3Rank(
                                rank1 = top1,
                                rank2 = top2,
                                rank3 = top3,
                            )
                        Top3Header(
                            top3Rank = top3Rank,
                            onClick = onItemClick,
                            windowWidth = windowWidth,
                        )
                    }
                }

                item {
                    if (this@apply.itemCount != 0) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.buy),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        )
                    }
                }

                when (windowWidth) {
                    WindowType.Compact -> {
                        items(count = coins.itemCount) { index ->
                            when (val item = coins[index]) {
                                is CoinUiModel.CoinsItem -> {
                                    if (item.coin.rank != 1 && item.coin.rank != 2 && item.coin.rank != 3) {
                                        CoinItem(
                                            image = item.coin.iconUrl,
                                            name = item.coin.name,
                                            currency = item.coin.symbol,
                                            price = item.coin.price,
                                            change = item.coin.change,
                                            onClick = {
                                                onItemClick(item.coin.uuid)
                                            },
                                        )
                                    }
                                }

                                CoinUiModel.InviteFriendItem -> InviteFriendItem(onClick = onInviteClick)

                                null -> Unit
                            }
                        }
                    }

                    WindowType.Medium -> {
                        item {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .heightIn(max = screenHeightDp),
                                contentPadding = PaddingValues(MaterialTheme.dimen.base2x),
                                verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.base),
                                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.base),
                                state = lazyGridState,
                            ) {
                                items(
                                    count = coins.itemCount,
                                ) { index ->
                                    when (val item = coins[index]) {
                                        is CoinUiModel.CoinsItem -> {
                                            CoinItem(
                                                image = item.coin.iconUrl,
                                                name = item.coin.name,
                                                currency = item.coin.symbol,
                                                price = item.coin.price,
                                                change = item.coin.change,
                                                onClick = {
                                                    onItemClick(item.coin.uuid)
                                                },
                                            )
                                        }

                                        CoinUiModel.InviteFriendItem -> InviteFriendItem(onClick = onInviteClick)
                                        null -> Unit
                                    }
                                }
                            }
                        }
                    }

                    WindowType.Expanded -> {
                        item {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .heightIn(max = screenHeightDp),
                                contentPadding = PaddingValues(MaterialTheme.dimen.base2x),
                                verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.base),
                                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.base),
                                state = lazyGridState,
                            ) {
                                items(
                                    count = coins.itemCount,
                                ) { index ->
                                    when (val item = coins[index]) {
                                        is CoinUiModel.CoinsItem -> {
                                            CoinItem(
                                                image = item.coin.iconUrl,
                                                name = item.coin.name,
                                                currency = item.coin.symbol,
                                                price = item.coin.price,
                                                change = item.coin.change,
                                                onClick = {
                                                    onItemClick(item.coin.uuid)
                                                },
                                            )
                                        }

                                        CoinUiModel.InviteFriendItem -> InviteFriendItem(onClick = onInviteClick)
                                        null -> Unit
                                    }
                                }
                            }
                        }
                    }
                }

                when {
                    loadState.append is LoadState.Loading -> {
                        item {
                            LoadingItem()
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = this@apply.loadState.append as LoadState.Error
                        val message = error.error.localizedMessage ?: "Can't load data"
                        item {
                            ErrorItem(
                                message = message,
                                onRetry = {
                                    this@apply.retry()
                                },
                            )
                        }
                    }

                    loadState.append.endOfPaginationReached -> {
                        if (this@apply.itemCount != 0) {
                            item {
                                // end item
                                HorizontalDivider(
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = MaterialTheme.dimen.small),
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
            }

            when (loadState.refresh) {
                is LoadState.Loading -> {
                    Box(
                        modifier = Modifier.matchParentSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        // should be FULL SCREEN LOADING
                        LoadingItem(enabledFullScreen = true)
                    }
                }

                is LoadState.Error -> {
                    val error = this@apply.loadState.refresh as LoadState.Error
                    val message = error.error.localizedMessage ?: "Can't load data"
                    Box(
                        modifier = Modifier.matchParentSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        // should be FULL SCREEN ERROR
                        ErrorItem(
                            message = message,
                            enabledFullScreen = true,
                            onRetry = {
                                this@apply.retry()
                            },
                        )
                    }
                }

                is LoadState.NotLoading -> Unit
            }
        }
    }
}
