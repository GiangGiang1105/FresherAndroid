package com.example.clientaidl.di

import android.content.Context
import com.example.clientaidl.data.datasource.StudentDataSource
import com.example.clientaidl.data.repository.StudentRepository
import com.example.clientaidl.screen.adapter.StudentRecyclerAdapter
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
    fun provideStudentDataSource(@ApplicationContext context: Context): StudentDataSource =
        StudentDataSource(context)

    @Provides
    @Singleton
    fun provideRepository(studentDataSource: StudentDataSource): StudentRepository =
        StudentRepository(studentDataSource)

    @Provides
    @Singleton
    fun provideStudentAdapter(): StudentRecyclerAdapter = StudentRecyclerAdapter()
}