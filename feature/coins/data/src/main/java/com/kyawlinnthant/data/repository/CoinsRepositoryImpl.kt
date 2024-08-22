package com.kyawlinnthant.data.repository

import androidx.paging.InvalidatingPagingSourceFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kyawlinnthant.data.dto.coins.Coin
import com.kyawlinnthant.data.dto.detail.CoinDetail
import com.kyawlinnthant.data.paging.CoinsPagingSource
import com.kyawlinnthant.data.service.CoinsApi
import com.kyawlinnthant.dispatcher.DispatcherModule
import com.kyawlinnthant.network.safeApiCall
import com.kyawlinnthant.util.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinsRepositoryImpl
    @Inject
    constructor(
        private val api: CoinsApi,
        @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher,
    ) : CoinsRepository {
        override suspend fun getCoins(keyword: String?): Flow<PagingData<Coin>> {
            val config =
                PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20,
                    prefetchDistance = 1,
                    enablePlaceholders = true,
                )
            val pagingSourceFactory =
                InvalidatingPagingSourceFactory {
                    CoinsPagingSource(
                        api = api,
                        keyword = keyword,
                    )
                }

            return Pager(
                config = config,
                pagingSourceFactory = pagingSourceFactory,
            ).flow.flowOn(io)
        }

        override suspend fun refreshCoins(size: Int): DataResult<List<Coin>> {
            return withContext(io) {
                when (
                    val response =
                        safeApiCall {
                            api.coins(
                                limit = size,
                                offset = 0,
                            )
                        }
                ) {
                    is DataResult.Failed ->
                        DataResult.Failed(
                            error = response.error,
                            message = response.message,
                        )

                    is DataResult.Success ->
                        DataResult.Success(
                            data = response.data.data?.coins ?: emptyList(),
                        )
                }
            }
        }

        override suspend fun getDetail(uuid: String): DataResult<CoinDetail> {
            return withContext(io) {
                when (
                    val response =
                        safeApiCall {
                            api.detail(uuid)
                        }
                ) {
                    is DataResult.Failed ->
                        DataResult.Failed(
                            error = response.error,
                            message = response.message,
                        )

                    is DataResult.Success ->
                        DataResult.Success(
                            data = response.data.data?.coin ?: CoinDetail(),
                        )
                }
            }
        }
    }
