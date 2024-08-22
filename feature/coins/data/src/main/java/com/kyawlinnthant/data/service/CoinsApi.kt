package com.kyawlinnthant.data.service

import com.kyawlinnthant.data.dto.coins.CoinsDTO
import com.kyawlinnthant.data.dto.detail.CoinDetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinsApi {
    companion object {
        const val COINS = "coins"
        const val DETAIL = "coin"
    }

    @GET(COINS)
    suspend fun coins(
        @Query("search") keyword: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int,
    ): Response<CoinsDTO>

    @GET("$DETAIL/{uuid}")
    suspend fun detail(
        @Path("uuid") uuid: String,
    ): Response<CoinDetailDTO>
}
