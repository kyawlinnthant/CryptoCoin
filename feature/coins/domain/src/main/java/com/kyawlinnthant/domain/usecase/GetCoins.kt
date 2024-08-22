package com.kyawlinnthant.domain.usecase

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.kyawlinnthant.data.repository.CoinsRepository
import com.kyawlinnthant.domain.vo.CoinUiModel
import com.kyawlinnthant.domain.vo.toVo
import com.kyawlinnthant.util.CoinDisplayFormatter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class GetCoins
    @Inject
    constructor(
        private val repo: CoinsRepository,
    ) {
        @OptIn(ExperimentalCoroutinesApi::class)
        suspend operator fun invoke(): Flow<PagingData<CoinUiModel>> {
            return repo.getCoins(
                keyword = null,
            ).mapLatest { pagingData ->
                pagingData.map { dto ->
                    CoinUiModel.CoinsItem(dto.toVo())
                }.insertSeparators { coinsItem: CoinUiModel.CoinsItem?, _ ->
                    coinsItem?.coin?.rank?.let { r ->
                        if (CoinDisplayFormatter.shouldShowInviteFriend(r)) {
                            return@insertSeparators CoinUiModel.InviteFriendItem
                        } else {
                            null
                        }
                    } ?: run {
                        null
                    }
                }
            }
        }
    }
