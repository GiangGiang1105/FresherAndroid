package com.example.exercise.data.repository

import com.example.exercise.base.BaseRepository
import com.example.exercise.data.network.service.MusicApiService
import com.example.exercise.utils.Constants

class MusicRepository(private val musicApiService: MusicApiService) : BaseRepository() {

    suspend fun getAllMusic() = safeApi {
        musicApiService.getAllMusic().music.onEach {
            it.source = Constants.BASE_URL + it.source
            it.image = Constants.BASE_URL + it.image
        }
    }
}