package com.example.clientstudent.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aidllibrary.entity.Student
import com.example.clientstudent.databinding.ItemStudentBinding

class StudentRecyclerAdapter : RecyclerView.Adapter<StudentRecyclerAdapter.StudentHolder>() {

    private var listStudents = mutableListOf<Student>()
    private var itemSetOnClickListener: ((student: Student) -> Unit)? = null

    fun setOnItemClickListener(itemOnClick: (student: Student) -> Unit) {
        itemSetOnClickListener = itemOnClick
    }

    fun updateData(newStudents:List<Student>?) {
        newStudents?.let { new ->
            val diffResult: DiffUtil.DiffResult = StudentDiffUtils(listStudents, new).let { diff ->
                DiffUtil.calculateDiff(diff)
            }
            listStudents.clear()
            listStudents.addAll(new)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder =
        StudentHolder(
            ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.bindData(listStudents[position])
    }

    override fun getItemCount(): Int = listStudents.size

    inner class StudentHolder(private val itemStudentBinding: ItemStudentBinding) :
        RecyclerView.ViewHolder(itemStudentBinding.root) {

        fun bindData(student: Student) {
            itemStudentBinding.student = student
            itemStudentBinding.onClickListener = itemSetOnClickListener
        }
    }
}