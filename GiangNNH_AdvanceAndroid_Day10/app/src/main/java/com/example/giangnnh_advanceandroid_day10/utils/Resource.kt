package com.example.giangnnh_advanceandroid_day10.utils

import com.example.giangnnh_advanceandroid_day10.base.Event

sealed class Resource<out T> {
    data class Success<out T>(val data: T?) : Resource<T>()

    data class Failure(
        val throwable: Event<Throwable>
    ) : Resource<Nothing>()

    data class Loading(
        val loading: Event<Boolean>
    ) : Resource<Nothing>()
}
