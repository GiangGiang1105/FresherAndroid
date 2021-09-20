package com.example.clientstudent.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.aidllibrary.controller.StudentServiceConnector
import com.example.aidllibrary.controller.StudentServiceController
import com.example.aidllibrary.entity.FailureResponse
import com.example.aidllibrary.entity.ResponseCode
import com.example.aidllibrary.entity.Student
import com.example.aidllibrary.entity.StudentResponse
import com.example.clientstudent.R
import com.example.clientstudent.app.ClientApplication
import com.example.clientstudent.screen.fragment.UpdateFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val studentServiceController: StudentServiceController
) : ViewModel(), LifecycleObserver, StudentServiceConnector.Callback {

    var students: MutableLiveData<List<Student>> = MutableLiveData()
        private set

    override fun onDeleteStudentResponse(response: StudentResponse) {
        if (response.responseCode == ResponseCode.OK) {
            ClientApplication.showToast(
                context,
                context.getString(R.string.success_delete_student, response.idStudent.toString())
            )
            students.value = response.students
        }
    }

    override fun onFailureResponse(response: FailureResponse) {
        when (response.responseCode) {
            ResponseCode.ERROR_SELECT_STUDENTS -> ClientApplication.showToast(
                context, context.getString(
                    R.string.error_select_student
                )
            )
            ResponseCode.ERROR_INSERT -> ClientApplication.showToast(
                context,
                context.getString(R.string.error_insert_student)
            )
            ResponseCode.ERROR_UPDATE -> ClientApplication.showToast(
                context,
                context.getString(R.string.error_update_student)
            )
            ResponseCode.ERROR_DELETE -> ClientApplication.showToast(
                context,
                context.getString(R.string.error_delete_student)
            )
        }
    }

    override fun onGetAllStudentResponse(response: StudentResponse) {
        if (response.responseCode == ResponseCode.OK) {
            ClientApplication.showToast(context, context.getString(R.string.success_select_student))
            Log.e("TAG", "onGetAllStudentResponse: ", )
            students.value = response.students
        }
    }

    override fun onInsertStudentResponse(response: StudentResponse) {
        if (response.responseCode == ResponseCode.OK) {
            ClientApplication.showToast(
                context,
                context.getString(R.string.success_insert_student, response.idStudent.toString())
            )
            students.value = response.students
            Log.e("TAG", "onInsertStudentResponse: ${response.students}", )
        }
        else {
            Log.e("TAG", "onInsertStudentResponse:error", )
        }
    }

    override fun onStudentServiceConnected() {
        ClientApplication.showToast(context, context.getString(R.string.success_connected))
        fetchAllStudents()
    }

    override fun onUpdateStudentResponse(response: StudentResponse) {
        if (response.responseCode == ResponseCode.OK) {
            ClientApplication.showToast(
                context,
                context.getString(R.string.success_update_student, response.idStudent.toString())
            )
            students.value = response.students
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        studentServiceController.addCallback(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        studentServiceController.removeCallback(this)
    }

    private fun fetchAllStudents() {
        studentServiceController.getAllStudent()
    }

    fun insertStudent(student: Student) {
        studentServiceController.insertStudent(student)
    }

    fun updateStudent(student: Student) {
        studentServiceController.updateStudent(student)
    }

    fun deleteStudent(student: Student) {
        studentServiceController.deleteStudent(student)
    }
}