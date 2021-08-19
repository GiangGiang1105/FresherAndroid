package com.example.giangnnh_advanceandroid_day4.base

import com.example.giangnnh_advanceandroid_day4.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApi(apiCall: suspend () -> T): Resource<T> = withContext(Dispatchers.IO) {
        try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> Resource.Error(
                    false,
                    throwable.code(),
                    throwable.response()?.body() as ResponseBody
                )
                else -> Resource.Error(true, null, null)
            }
        }
    }
}