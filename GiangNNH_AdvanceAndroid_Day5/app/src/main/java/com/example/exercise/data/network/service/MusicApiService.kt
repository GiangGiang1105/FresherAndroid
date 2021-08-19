package com.example.exercise.data.network.service

import com.example.exercise.data.model.MusicResponse
import retrofit2.Call
import retrofit2.http.GET

interface MusicApiService {

    @GET("music.json")
    suspend fun getAllMusic(): MusicResponse
}