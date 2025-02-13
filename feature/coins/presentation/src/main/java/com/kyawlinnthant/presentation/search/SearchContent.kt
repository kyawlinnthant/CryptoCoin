package com.kyawlinnthant.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.kyawlinnthant.domain.vo.CoinVo
import com.kyawlinnthant.presentation.item.CoinItem
import com.kyawlinnthant.presentation.item.EmptySearchItem
import com.kyawlinnthant.presentation.item.ErrorItem
import com.kyawlinnthant.presentation.item.InviteFriendItem
import com.kyawlinnthant.presentation.item.LoadingItem
import com.kyawlinnthant.theme.WindowType
import com.kyawlinnthant.theme.dimen
import com.kyawlinnthant.util.CoinDisplayFormatter

@Composable
fun SearchContent(
    searchCoins: LazyPagingItems<CoinVo>,
    paddingValues: PaddingValues,
    windowWidth: WindowType,
    onItemClick: (String) -> Unit,
    onInviteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp

    searchCoins.apply {
        LazyColumn(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            state = lazyListState,
            contentPadding = PaddingValues(MaterialTheme.dimen.base2x),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.base),
        ) {
            item {
                if (this@apply.itemCount != 0) {
                    Text(
                        text = stringResource(id = R.string.buy),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    )
                }
            }
            when (windowWidth) {
                WindowType.Compact -> {
                    items(
                        count = searchCoins.itemCount,
                    ) { index ->
                        val currentIndex = searchCoins[index]
                        currentIndex?.let {
                            val shouldShowInvite =
                                CoinDisplayFormatter.shouldShowInviteFriend(index + 1)
                            Column {
                                CoinItem(
                                    image = currentIndex.iconUrl,
                                    name = currentIndex.name,
                                    currency = currentIndex.symbol,
                                    price = currentIndex.price,
                                    change = currentIndex.change,
                                    onClick = {
                                        onItemClick(currentIndex.uuid)
                                    },
                                )
                                if (shouldShowInvite) {
                                    Spacer(modifier = Modifier.height(MaterialTheme.dimen.base))
                                    InviteFriendItem(onClick = onInviteClick)
                                }
                            }
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
                                count = searchCoins.itemCount,
                            ) { index ->
                                val item = searchCoins[index]
                                item?.let {
                                    CoinItem(
                                        image = it.iconUrl,
                                        name = it.name,
                                        currency = it.symbol,
                                        price = it.price,
                                        change = it.change,
                                        onClick = {
                                            onItemClick(it.uuid)
                                        },
                                    )
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
                                count = searchCoins.itemCount,
                            ) { index ->
                                val item = searchCoins[index]
                                item?.let {
                                    CoinItem(
                                        image = it.iconUrl,
                                        name = it.name,
                                        currency = it.symbol,
                                        price = it.price,
                                        change = it.change,
                                        onClick = {
                                            onItemClick(it.uuid)
                                        },
                                    )
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
                    item {
                        if (this@apply.itemCount != 0) {
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
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    // should be FULL SCREEN LOADING
                    LoadingItem(enabledFullScreen = true)
                }
            }

            is LoadState.Error -> {
                val error = this.loadState.refresh as LoadState.Error
                val message = error.error.localizedMessage ?: "Can't load data"
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
        if (loadState.append.endOfPaginationReached) {
            if (this@apply.itemCount == 0) {
                EmptySearchItem()
            }
        }
    }
}
