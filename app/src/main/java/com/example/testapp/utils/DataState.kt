package com.example.testapp.utils

sealed class DataState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : DataState<T>(data = data)
    class Error<T>(errorMessage: String) : DataState<T>(message = errorMessage)
}