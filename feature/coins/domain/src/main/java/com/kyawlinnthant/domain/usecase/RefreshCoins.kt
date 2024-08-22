package com.kyawlinnthant.domain.usecase

import com.kyawlinnthant.data.repository.CoinsRepository
import com.kyawlinnthant.domain.vo.CoinVo
import com.kyawlinnthant.domain.vo.toVo
import com.kyawlinnthant.util.DataResult
import javax.inject.Inject

class RefreshCoins
    @Inject
    constructor(
        private val repo: CoinsRepository,
    ) {
        suspend operator fun invoke(size: Int): DataResult<List<CoinVo>> {
            return when (val response = repo.refreshCoins(size)) {
                is DataResult.Failed ->
                    DataResult.Failed(
                        error = response.error,
                        message = response.message,
                    )

                is DataResult.Success -> {
                    DataResult.Success(data = response.data.map { it.toVo() })
                }
            }
        }
    }
