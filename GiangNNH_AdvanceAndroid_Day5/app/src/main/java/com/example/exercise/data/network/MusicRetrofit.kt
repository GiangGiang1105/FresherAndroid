package com.example.exercise.data.network

import android.util.Log
import com.example.exercise.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MusicRetrofit() {

    fun <Api> retrofit(api: Class<Api>): Api {
        val okHttpClient = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder().client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build().create(api)
    }
}
