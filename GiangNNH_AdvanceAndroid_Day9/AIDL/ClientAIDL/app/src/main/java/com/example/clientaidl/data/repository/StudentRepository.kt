package com.example.clientaidl.data.repository

import com.example.clientaidl.base.BaseRepository
import com.example.clientaidl.data.datasource.StudentDataSource
import com.example.clientaidl.utils.Resource
import com.example.serveraidl.data.model.Student
import javax.inject.Inject

class StudentRepository @Inject constructor(private val studentDataSource: StudentDataSource) :
    BaseRepository() {

    suspend fun getAllStudents() = safeApi {
        studentDataSource.getAllStudents()
    }

    suspend fun insertStudent( student: Student) = safeApi {
        studentDataSource.insertStudent(student)
    }

    suspend fun updateStudent(student: Student) = safeApi {
        studentDataSource.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student) = safeApi {
        studentDataSource.deleteStudent(student)
    }

    fun connectServer(){
        studentDataSource.connectServer()
    }

    fun stopServerService() {
        studentDataSource.stopServerService()
    }

}