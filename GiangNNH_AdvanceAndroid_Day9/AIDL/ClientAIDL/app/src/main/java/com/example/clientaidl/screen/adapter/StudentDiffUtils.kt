package com.example.clientaidl.screen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.serveraidl.data.model.Student

class StudentDiffUtils(
    private val oldStudents: List<Student>,
    private val newStudents: List<Student>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldStudents.size

    override fun getNewListSize(): Int = newStudents.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldStudents[oldItemPosition].id == newStudents[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldStudents == newStudents
}