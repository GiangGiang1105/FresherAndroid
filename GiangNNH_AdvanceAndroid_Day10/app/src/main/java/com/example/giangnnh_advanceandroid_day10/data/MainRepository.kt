package com.example.giangnnh_advanceandroid_day10.data

import com.example.giangnnh_advanceandroid_day10.data.service.ICityService
import com.example.giangnnh_advanceandroid_day10.data.service.IWeatherService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val iWeatherService: IWeatherService,
    private val iCityService: ICityService
) {

    fun getWeatherOfCity(name: String) = iWeatherService.getWeatherOfCity(name = name)

    fun getCitiesByNamePrefix(namePrefix: String) = iCityService.getCitiesByNamePrefix(namePrefix)
}