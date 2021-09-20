package com.example.clientstudent.di

import android.content.Context
import com.example.aidllibrary.controller.StudentServiceController
import com.example.clientstudent.screen.adapter.StudentRecyclerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClientModule {

    @Provides
    @Singleton
    fun providesStudentServiceController(@ApplicationContext context: Context): StudentServiceController =
        StudentServiceController(context)

    @Provides
    @Singleton
    fun provideStudentAdapter(): StudentRecyclerAdapter = StudentRecyclerAdapter()
}