package com.example.giangnnh_advanceandroid_day10.data.service

import com.example.giangnnh_advanceandroid_day10.data.model.weather.WeatherData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherService {

    @GET("weather")
    fun getWeatherOfCity(
        @Query("q") name: String,
        @Query("appid") appid: String = API_KEY
    ): Observable<WeatherData>

    companion object {
        private const val API_KEY = "52e78053556d58273b9d5b7c6a579197"
    }
}