package com.example.assignment4.utils

/**
 * Used for API response.
 * It tells whether a response it successful or not.
 * **/

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
