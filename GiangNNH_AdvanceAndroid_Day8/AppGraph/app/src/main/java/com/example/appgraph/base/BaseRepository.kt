package com.example.appgraph.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    suspend fun <T> safeApi(apiCall: suspend () -> T): T = withContext(Dispatchers.IO) {
        apiCall.invoke()
    }
}