package com.example.exercise.utils

import okhttp3.ResponseBody


sealed class Resource<out T> {

    data class Success<out T>(val data: T?) : Resource<T>()

    data class Error(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val response: ResponseBody?
    ) :
        Resource<Nothing>()
}
