package com.example.serveraidl.di

import android.content.Context
import androidx.room.Room
import com.example.serveraidl.data.dao.IStudentDao
import com.example.serveraidl.data.database.StudentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StudentDatabase =
        Room.databaseBuilder(context, StudentDatabase::class.java, "StudentDatabase.db").build()

    @Provides
    @Singleton
    fun provdeStudentDao(studentDatabase: StudentDatabase): IStudentDao =
        studentDatabase.getStudentDao()
}