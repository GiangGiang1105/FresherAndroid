package com.example.serverstudent.model.dao

import androidx.room.*
import com.example.serverstudent.model.entity.StudentDetail

@Dao
interface IStudentDao {

    @Query("SELECT * FROM Student")
    fun getAllStudents(): List<StudentDetail>

    @Insert
    fun insertStudent(student: StudentDetail): Long

    @Update
    fun updateStudent(student: StudentDetail): Int

    @Delete
    fun deleteStudent(student: StudentDetail): Int

}