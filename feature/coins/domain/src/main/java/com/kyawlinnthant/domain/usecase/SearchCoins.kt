package com.kyawlinnthant.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.kyawlinnthant.data.repository.CoinsRepository
import com.kyawlinnthant.domain.vo.CoinVo
import com.kyawlinnthant.domain.vo.toVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchCoins
    @Inject
    constructor(
        private val repo: CoinsRepository,
    ) {
        suspend operator fun invoke(keyword: String): Flow<PagingData<CoinVo>> {
            return repo.getCoins(
                keyword = keyword,
            ).map { pagingData ->
                pagingData.map { dto ->
                    dto.toVo()
                }
            }
        }
    }
