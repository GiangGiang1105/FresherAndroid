package com.example.giangnnh_advanceandroid_day10.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherOKHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CitiesOKHttpClient