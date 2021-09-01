package com.example.frand01_advandfinalth_giangnnh.di

import android.content.Context
import androidx.room.Room
import com.example.frand01_advandfinalth_giangnnh.data.dao.ITireDefaultDao
import com.example.frand01_advandfinalth_giangnnh.data.dao.ITireSensorDao
import com.example.frand01_advandfinalth_giangnnh.data.database.TireDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): TireDatabase =
        Room.databaseBuilder(context, TireDatabase::class.java, "TireDatabase.db").build()

    @Provides
    @Singleton
    fun providesTireDefaultDao(tireDatabase: TireDatabase): ITireDefaultDao =
        tireDatabase.getTireDefaultDao()

    @Provides
    @Singleton
    fun provideTireSensorDao(tireDatabase: TireDatabase): ITireSensorDao =
        tireDatabase.getTireSensorDao()

}