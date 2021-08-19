package com.example.giangnnh_advanceandroid_day4.utils

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val data: T?) : Resource<T>()

    data class Error(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val response: ResponseBody?
    ) : Resource<Nothing>()
}