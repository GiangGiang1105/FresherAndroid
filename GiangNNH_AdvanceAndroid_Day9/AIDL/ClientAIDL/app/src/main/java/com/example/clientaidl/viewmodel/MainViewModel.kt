package com.example.clientaidl.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clientaidl.data.repository.StudentRepository
import com.example.clientaidl.utils.Resource
import com.example.serveraidl.data.model.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: StudentRepository) : ViewModel() {

    var students: MutableLiveData<Resource<List<Student>>> = MutableLiveData()
        private set
    var resultInsert: MutableLiveData<Resource<Long>> = MutableLiveData()
        private set
    var resultUpdate: MutableLiveData<Resource<Int>> = MutableLiveData()
        private set
    var resultDelete: MutableLiveData<Resource<Int>> = MutableLiveData()
        private set


    fun getAllStudents() = viewModelScope.launch {
        students.value = repository.getAllStudents()
    }

    fun insertStudent(student: Student) = viewModelScope.launch {
        resultInsert.value = repository.insertStudent(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        resultUpdate.value = repository.updateStudent(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch {
        resultDelete.value = repository.deleteStudent(student)
    }

    fun connectServer() {
        repository.connectServer()
    }

    fun stopServerService() {
        repository.stopServerService()
    }
}