package com.example.clientaidl.base

import com.example.clientaidl.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    suspend fun <T> safeApi(apiCall: suspend () -> T?): Resource<T> = withContext(Dispatchers.IO) {
        try {
            Resource.Success(apiCall.invoke())
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }
}