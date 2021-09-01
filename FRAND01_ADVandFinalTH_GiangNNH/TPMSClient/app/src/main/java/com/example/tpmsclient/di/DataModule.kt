package com.example.tpmsclient.di

import android.content.Context
import com.example.tpmsclient.data.datasource.TireDataSource
import com.example.tpmsclient.data.repository.TireRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDataSource(@ApplicationContext context: Context): TireDataSource =
        TireDataSource(context)

    @Provides
    @Singleton
    fun provideRepository(tireDataSource: TireDataSource): TireRepository =
        TireRepository(tireDataSource)

}