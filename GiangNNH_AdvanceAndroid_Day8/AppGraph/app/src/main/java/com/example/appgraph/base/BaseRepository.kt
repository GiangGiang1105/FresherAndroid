package com.example.appgraph.base

import com.example.appgraph.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    suspend fun <T> safeApi(apiCall: suspend () -> T): Resource<T> = withContext(Dispatchers.IO) {
        try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Resource.Error(throwable.message)
        }
    }
}