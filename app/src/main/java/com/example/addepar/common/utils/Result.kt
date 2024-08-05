package com.example.addepar.common.utils



sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?): Result<T>(data = data)
    class Error<T>(message: String?): Result<T>(message = message)
    class Loading<T>(val isLoading: Boolean = false): Result<T>()
}