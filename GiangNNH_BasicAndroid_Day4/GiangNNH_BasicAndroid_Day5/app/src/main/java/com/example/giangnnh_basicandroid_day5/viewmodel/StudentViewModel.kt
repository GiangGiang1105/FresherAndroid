package com.example.giangnnh_basicandroid_day5.viewmodel

import androidx.lifecycle.ViewModel
import com.example.giangnnh_basicandroid_day5.data.model.Student

class StudentViewModel : ViewModel() {

    var listStudent = mutableListOf<Student>()

    fun addStudent(student: Student) {

        listStudent.add(student)
    }
}