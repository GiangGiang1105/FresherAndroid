package com.example.clientaidl.data.datasource

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.example.serveraidl.IStudentBinder
import com.example.serveraidl.data.model.Student

class StudentDataSource(private val context: Context) {

    private val intentService = Intent(ACTION).apply {
        setClassName(PACKAGE_NAME, CLASS_NAME)
    }
    private var binder: IStudentBinder? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = IStudentBinder.Stub.asInterface(service)

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }

    fun connectServer() {
        context.bindService(intentService, connection, Context.BIND_AUTO_CREATE)
    }

    fun getAllStudents(): MutableList<Student>? {
        Log.e("TAG", "getAllStudents:${binder} ")
        return binder?.allStudents
    }

    fun insertStudent(student: Student): Long? =
        binder?.insertStudent(student)


    fun updateStudent(student: Student): Int? =
        binder?.updateStudent(student)


    fun deleteStudent(student: Student): Int? =
        binder?.deleteStudent(student)

    fun stopServerService() {
        context.unbindService(connection)
        context.stopService(intentService)
    }

    companion object {
        private const val ACTION = "com.example.serveraidl.service.CONNECT_SERVER_DATABASE"
        private const val PACKAGE_NAME = "com.example.serveraidl"
        private const val CLASS_NAME = "com.example.serveraidl.service.ServerService"
    }
}