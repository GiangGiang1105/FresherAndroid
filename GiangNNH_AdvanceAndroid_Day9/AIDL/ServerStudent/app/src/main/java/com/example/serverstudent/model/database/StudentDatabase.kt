package com.example.serverstudent.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.connectorlibrary.enitity.Static
import com.example.serverstudent.model.dao.IStudentDao
import com.example.serverstudent.model.entity.StudentDetail
import com.example.serverstudent.model.entity.User

@Database(entities = [StudentDetail::class, User::class, Static::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun getStudentDao(): IStudentDao
}