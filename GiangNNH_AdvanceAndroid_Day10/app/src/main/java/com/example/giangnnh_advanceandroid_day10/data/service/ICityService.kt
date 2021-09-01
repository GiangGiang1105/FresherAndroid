package com.example.giangnnh_advanceandroid_day10.data.service

import io.reactivex.rxjava3.core.Observable
import com.example.giangnnh_advanceandroid_day10.data.model.city.ListCity
import retrofit2.http.GET
import retrofit2.http.Query

interface ICityService {

    @GET("api.php")
    fun getCitiesByNamePrefix(@Query("namePrefix") namePrefix: String = ""): Observable<ListCity>
}