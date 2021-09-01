package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.dao.ChartDao
import com.example.myapplication.data.database.ChartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ChartDatabase =
        Room.databaseBuilder(appContext, ChartDatabase::class.java, "chart.db").build()

    @Provides
    @Singleton
    fun provideDao(database: ChartDatabase): ChartDao = database.getChartItemDao()
}
