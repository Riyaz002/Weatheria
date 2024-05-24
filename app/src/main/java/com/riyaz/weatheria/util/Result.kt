package com.riyaz.weatheria.util

sealed interface Result {
    data class Success<T>(val data: T?): Result
    data class Error(val error: Error?): Result
}