package com.example.serverstudent.di

import android.content.Context
import android.os.RemoteCallbackList
import androidx.room.Room
import com.example.aidllibrary.controller.IStudentServiceCallback
import com.example.serverstudent.model.dao.IStudentDao
import com.example.serverstudent.model.database.StudentDatabase
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

    @Provides
    @Singleton
    fun providesCoroutineScope() = CoroutineScope(Dispatchers.IO)

    @Provides
    @Singleton
    fun providesStudentCallback(): RemoteCallbackList<IStudentServiceCallback> =
        RemoteCallbackList()
}