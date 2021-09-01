package com.example.serveraidl.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.serveraidl.data.dao.IStudentDao
import com.example.serveraidl.data.model.Student

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase(){

    abstract fun getStudentDao() : IStudentDao
}