package com.company.nond.utils.network

sealed class NetworkResponse<out T : Any> {
    /**
     * Success response with body
     */
    data class Success<T : Any>(val body: T) : NetworkResponse<T>()

    /**
     * Error types can be extended and categorized in future
     */
    data class Fail(val t: Throwable) : NetworkResponse<Nothing>()

}
