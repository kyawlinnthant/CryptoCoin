package com.kyawlinnthant.data.repository

import androidx.paging.PagingData
import com.kyawlinnthant.data.dto.coins.Coin
import com.kyawlinnthant.data.dto.detail.CoinDetail
import com.kyawlinnthant.util.DataResult
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {
    suspend fun getCoins(keyword: String?): Flow<PagingData<Coin>>

    suspend fun getDetail(uuid: String): DataResult<CoinDetail>

    suspend fun refreshCoins(size: Int): DataResult<List<Coin>>
}
