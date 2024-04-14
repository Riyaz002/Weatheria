package com.riyaz.weatheria.util

sealed interface Resource {
    data class Success<T>(val data: T?): Resource
    data class Error(val error: Error?): Resource
}