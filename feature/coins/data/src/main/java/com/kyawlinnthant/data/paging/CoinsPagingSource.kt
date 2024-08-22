package com.kyawlinnthant.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kyawlinnthant.data.dto.coins.Coin
import com.kyawlinnthant.data.service.CoinsApi
import com.kyawlinnthant.network.safeApiCall
import com.kyawlinnthant.util.DataResult
import javax.inject.Inject

class CoinsPagingSource
    @Inject
    constructor(
        private val api: CoinsApi,
        private val keyword: String?,
    ) : PagingSource<Int, Coin>() {
        private val startingOffset = 0

        override fun getRefreshKey(state: PagingState<Int, Coin>): Int {
            return startingOffset
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
            val currentOffset = params.key ?: startingOffset
            Log.d("baby", "load = $currentOffset")

            return when (
                val response =
                    safeApiCall {
                        api.coins(
                            keyword = keyword,
                            offset = currentOffset,
                            limit = params.loadSize,
                        )
                    }
            ) {
                is DataResult.Failed -> {
                    Log.d("baby", "load fail = $currentOffset")
                    LoadResult.Error(response.error ?: Throwable(message = response.message))
                }

                is DataResult.Success -> {
                    Log.d("baby", "load success = $currentOffset")
                    val coins = response.data.data?.coins
                    val nextKey = currentOffset + params.loadSize
                    val isEnd = coins?.isEmpty() ?: true

                    Log.d("baby", "load coins = $coins")
                    Log.d("baby", "load nextKey = $nextKey")
                    Log.d("baby", "load isEnd = $isEnd")

                    LoadResult.Page(
                        data = coins.orEmpty(),
                        prevKey = null,
                        nextKey = if (isEnd) null else nextKey,
                    )
                }
            }
        }
    }
