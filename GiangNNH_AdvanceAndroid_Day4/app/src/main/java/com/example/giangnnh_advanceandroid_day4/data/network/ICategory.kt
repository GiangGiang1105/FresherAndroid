package com.example.giangnnh_advanceandroid_day4.data.network

import com.example.giangnnh_advanceandroid_day4.data.model.CategoryFood
import retrofit2.http.GET

interface ICategory {

    @GET("categories.php")
    suspend fun getAllCategories(): CategoryFood
}