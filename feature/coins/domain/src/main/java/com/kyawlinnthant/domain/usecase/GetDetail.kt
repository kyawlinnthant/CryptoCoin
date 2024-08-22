package com.kyawlinnthant.domain.usecase

import com.kyawlinnthant.data.repository.CoinsRepository
import com.kyawlinnthant.domain.vo.CoinDetailVo
import com.kyawlinnthant.domain.vo.RequestState
import com.kyawlinnthant.domain.vo.toVo
import com.kyawlinnthant.util.DataResult
import javax.inject.Inject

class GetDetail
    @Inject
    constructor(
        private val repo: CoinsRepository,
    ) {
        suspend operator fun invoke(uuid: String): RequestState<CoinDetailVo> {
            return when (val response = repo.getDetail(uuid)) {
                is DataResult.Failed -> RequestState.Error(message = response.message)
                is DataResult.Success -> RequestState.Success(data = response.data.toVo())
            }
        }
    }
