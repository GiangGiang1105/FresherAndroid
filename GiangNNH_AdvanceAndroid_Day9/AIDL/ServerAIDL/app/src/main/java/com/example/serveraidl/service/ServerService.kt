package com.example.serveraidl.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.serveraidl.IStudentBinder
import com.example.serveraidl.data.dao.IStudentDao
import com.example.serveraidl.data.model.Student
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServerService : Service() {

    @Inject
    lateinit var studentDao: IStudentDao

    private val binder = object : IStudentBinder.Stub() {
        override fun getAllStudents(): MutableList<Student> {
            Log.e("TAG", "getAllStudents: ", )
           return  studentDao.getAllStudents() as MutableList<Student>
        }

        override fun insertStudent(student: Student): Long = studentDao.insertStudent(student)

        override fun updateStudent(student: Student): Int = studentDao.updateStudent(student)

        override fun deleteStudent(student: Student): Int = studentDao.deleteStudent(student)

    }

    override fun onBind(intent: Intent): IBinder {
        Log.e("TAG", "onBind: ", )
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
}