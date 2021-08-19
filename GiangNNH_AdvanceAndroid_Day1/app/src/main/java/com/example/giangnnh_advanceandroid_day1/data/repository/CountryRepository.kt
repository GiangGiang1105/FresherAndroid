package com.example.giangnnh_advanceandroid_day1.data.repository

import com.example.giangnnh_advanceandroid_day1.base.BaseRepository
import com.example.giangnnh_advanceandroid_day1.data.model.network.CountryApiService
import com.example.giangnnh_advanceandroid_day1.utils.SingletonHolder

class CountryRepository(private val countryApiService: CountryApiService) : BaseRepository() {

    suspend fun getAllCountry() = safeApi {
        countryApiService.getAllCountry()
    }

    companion object : SingletonHolder<CountryRepository, CountryApiService>(::CountryRepository)
}