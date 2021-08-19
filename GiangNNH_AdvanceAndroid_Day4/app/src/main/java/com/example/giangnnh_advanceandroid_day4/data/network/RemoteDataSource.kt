package com.example.giangnnh_advanceandroid_day4.data.network

import com.example.giangnnh_advanceandroid_day4.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    fun <Api> getRetrofit(api: Class<Api>): Api {
        val okHttpClient = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(this)
        }

        return Retrofit.Builder().client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.BASE_URL).build()
            .create(api)
    }
}