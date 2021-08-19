package com.example.giangnnh_basicandroid_day5.data.model

import java.io.Serializable

data class Student(
    var name: String = "",
    var age: Int = 0,
    var mClass: String = "",
    var avgScore: Int = 0
) : Serializable
