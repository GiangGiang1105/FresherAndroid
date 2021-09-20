package com.example.clientstudent.screen.fragment

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.SearchView
import com.example.aidllibrary.entity.Student
import com.example.clientstudent.R
import com.example.clientstudent.app.ClientApplication
import com.example.clientstudent.base.BaseFragment
import com.example.clientstudent.databinding.FragmentStudentsBinding
import com.example.clientstudent.screen.adapter.StudentRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StudentsFragment : BaseFragment<FragmentStudentsBinding>() {

    @Inject
    lateinit var studentAdapter: StudentRecyclerAdapter
    private var listStudents: List<Student> = listOf()

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentStudentsBinding =
        FragmentStudentsBinding.inflate(layoutInflater)

    override fun init() {
        initRecyclerView()
        initListStudents()
        handleItemStudent()
        searchAStudent()
    }

    private fun initRecyclerView() {
        binding.studentsRecyclerView.apply {
            adapter = studentAdapter
            setHasFixedSize(false)
        }
    }

    private fun initListStudents() {
        viewModel.students.observe(this, {
            studentAdapter.updateData(it)
            listStudents = it
        })
    }

    private fun handleItemStudent() {
        studentAdapter.setOnItemClickListener {
            buildAlert(it)
        }
    }

    private fun buildAlert(student: Student) {
        val builder: AlertDialog.Builder = activity?.let {
            AlertDialog.Builder(it)
        } ?: throw IllegalStateException("Activity cannot be null")
        builder.let {
            it.setMessage(R.string.dialog_message)
            it.setTitle(R.string.dialog_title)
            it.setPositiveButton(R.string.posi_delete) { dialog, _ ->
                buildAlertDelete(student)
                dialog.dismiss()
            }
            it.setNegativeButton(R.string.posi_update) { _, _ ->
                val action =
                    StudentsFragmentDirections.actionStudentsFragmentToUpdateFragment(student)
                controller.navigate(action)
            }
            val alert = it.create()
            alert.show()
        }

    }

    private fun buildAlertDelete(student: Student) {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.let {
            it.setMessage(R.string.dialog_message_delete)
            it.setTitle(R.string.dialog_title_delete)
            it.setPositiveButton(R.string.posi_yes) { dialog, _ ->
                viewModel.deleteStudent(student)
                dialog.dismiss()
            }
            it.setNegativeButton(R.string.nav_no) { dialog, _ ->
                dialog.dismiss()
            }
            val alert = it.create()
            alert.show()
        }
    }

    private fun searchAStudent() {
        binding.searchStudent.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")) studentAdapter.updateData(listStudents) else
                    newText?.let {
                        listStudents.filter {
                            it.name.contains(newText)
                        }.let {
                            if (it.isEmpty()) {
                                ClientApplication.showToast(requireContext(), "Student null ")
                                studentAdapter.updateData(listStudents)
                            } else studentAdapter.updateData(listOf(it.first()))
                        }
                    }
                return true
            }
        })
    }

}