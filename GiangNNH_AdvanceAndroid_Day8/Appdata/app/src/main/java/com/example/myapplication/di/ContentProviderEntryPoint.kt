package com.example.myapplication.di

import com.example.myapplication.data.dao.ChartDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface ContentProviderEntryPoint {
    fun getDao(): ChartDao
}