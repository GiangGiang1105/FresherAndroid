package com.example.giangnnh_advanceandroid_day1.data.model.network

import com.example.giangnnh_advanceandroid_day1.data.model.Country
import retrofit2.http.GET

interface CountryApiService {

    @GET("countries.json")
    suspend fun getAllCountry() : List<Country>
}