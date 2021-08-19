package com.example.giangnnh_advanceandroid_day1.data.model.network

import com.example.giangnnh_advanceandroid_day1.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryRetrofit() {

    fun <Api> retrofit(api: Class<Api>): Api {
        val okHttpClient = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder().client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(Constants.BASE_URL).build().create(api)
    }
}