package com.kyawlinnthant.network

import com.kyawlinnthant.util.DataResult
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.net.SocketTimeoutException

inline fun <reified T> safeApiCall(
    json: Json = Json { ignoreUnknownKeys = true },
    apiCall: () -> Response<T>,
): DataResult<T> {
    return try {
        val response = apiCall()
        // 2x
        if (response.isSuccessful) {
            val body = response.body()
            DataResult.Success(data = body!!)
        } else {
            // 4x,5x
            val errorBody = response.errorBody()
            val errorResponse = json.decodeFromString<ErrorResponse>(errorBody!!.string())
            DataResult.Failed(
                message = errorResponse.message,
            )
        }
    } catch (e: SocketTimeoutException) {
        DataResult.Failed(error = e, message = "Something went wrong")
    }
}
