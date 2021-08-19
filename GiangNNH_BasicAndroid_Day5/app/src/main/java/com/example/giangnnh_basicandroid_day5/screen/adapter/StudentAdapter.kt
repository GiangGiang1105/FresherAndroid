package com.example.giangnnh_basicandroid_day5.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.giangnnh_basicandroid_day5.R
import com.example.giangnnh_basicandroid_day5.data.model.Student
import com.example.giangnnh_basicandroid_day5.databinding.ItemStudentBinding

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.Holder>() {

    private var listStudents = mutableListOf<Student>()

    fun updateData(list: List<Student>?) {
        list?.let {
            this.listStudents.clear()
            this.listStudents.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(listStudents[position])
    }

    override fun getItemCount() = listStudents.size

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemStudentBinding = ItemStudentBinding.bind(itemView)

        fun bindData(student: Student) {
            itemStudentBinding.apply {
                textName.text = student.name
                textAge.text = student.age.toString()
                textClass.text = student.mClass
                textAvgScore.text = student.avgScore.toString()
            }
        }
    }
}