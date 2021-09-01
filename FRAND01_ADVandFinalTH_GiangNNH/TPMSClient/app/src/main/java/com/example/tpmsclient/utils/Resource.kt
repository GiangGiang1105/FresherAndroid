package com.example.tpmsclient.utils

sealed class Resource<out T> {

    data class Success<out T>(val data: T?) : Resource<T>()

    data class Error(
        val exception: String?
    ) :
        Resource<Nothing>()
}