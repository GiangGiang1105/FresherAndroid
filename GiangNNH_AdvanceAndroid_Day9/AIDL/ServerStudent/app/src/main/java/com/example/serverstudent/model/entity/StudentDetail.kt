package com.example.serverstudent.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aidllibrary.entity.Student

@Entity(tableName = "Student")
data class StudentDetail(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var age: Int = 0,
    var math: Float = 0f,
    var physical: Float = 0f,
    var chemistry: Float = 0f,
    var english: Float = 0f,
    var literature: Float = 0f
)