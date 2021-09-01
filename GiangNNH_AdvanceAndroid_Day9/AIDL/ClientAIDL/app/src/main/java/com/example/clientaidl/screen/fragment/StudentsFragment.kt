package com.example.clientaidl.screen.fragment

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.SearchView
import android.widget.Toast
import com.example.clientaidl.R
import com.example.clientaidl.base.BaseFragment
import com.example.clientaidl.databinding.FragmentStudentsBinding
import com.example.clientaidl.screen.adapter.StudentRecyclerAdapter
import com.example.clientaidl.utils.Resource
import com.example.serveraidl.data.model.Student
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
        listenerResultDelete()
        searchAStudent()
        viewModel.connectServer()
        viewModel.getAllStudents()
    }

    private fun initRecyclerView() {
        binding.studentsRecyclerView.apply {
            adapter = studentAdapter
            setHasFixedSize(false)
        }
    }

    private fun initListStudents() {
        viewModel.students.observe(this, {
            when (it) {
                is Resource.Success -> {
                    if (it.data != null) {
                        studentAdapter.updateData(it.data)
                        listStudents = it.data
                    } else viewModel.getAllStudents()
                }
                is Resource.Error -> Log.e(TAG, "initListStudents: ${it.exception}")
            }
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

    private fun listenerResultDelete() {
        viewModel.resultDelete.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { result ->
                        if (result > 0) {
                            showToast(getString(R.string.mess_delete_success))
                            viewModel.getAllStudents()
                        } else {
                            showToast(getString(R.string.mess_delete_unsuccess))
                        }
                    } ?: run {
                        Log.d(TAG, "listenerResultDelete: result delete null")
                    }
                }
                is Resource.Error -> Log.e(TAG, "listenerResultDelete: ${it.exception}")
            }
        })
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
                                showToast("Student null ")
                                studentAdapter.updateData(listStudents)
                            } else studentAdapter.updateData(listOf(it.first()))
                        }
                    }
                return true
            }
        })
    }

    private fun showToast(messenger: String) {
        Toast.makeText(
            context,
            messenger,
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        private const val TAG = "StudentsFragment"
    }
}