package com.example.serveraidl.data.dao

import androidx.room.*
import com.example.serveraidl.data.model.Student

@Dao
interface IStudentDao {

    @Query("SELECT * FROM Student")
    fun getAllStudents(): List<Student>

    @Insert
    fun insertStudent(student: Student): Long

    @Update
    fun updateStudent(student: Student): Int

    @Delete
    fun deleteStudent(student: Student): Int
}