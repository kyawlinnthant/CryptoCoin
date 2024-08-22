package com.kyawlinnthant.util

sealed class DataResult<out T>(
    open val data: T? = null,
) {
    data class Success<out T>(override val data: T) : DataResult<T>()

    data class Failed(val error: Throwable? = null, val message: String) : DataResult<Nothing>()
}
