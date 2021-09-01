package com.example.day7.di

import android.content.Context
import androidx.room.Room
import com.example.day7.data.database.TransactionDatabase
import com.example.day7.data.database.dao.TransactionDao
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
    fun provideDatabase(@ApplicationContext appContext: Context): TransactionDatabase =
        Room.databaseBuilder(appContext, TransactionDatabase::class.java, "transaction.db").build()

    @Provides
    @Singleton
    fun provideLogDao(database: TransactionDatabase): TransactionDao = database.transactionDao()
}