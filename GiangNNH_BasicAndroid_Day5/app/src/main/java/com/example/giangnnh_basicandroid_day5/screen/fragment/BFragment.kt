package com.example.giangnnh_basicandroid_day5.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.giangnnh_basicandroid_day5.R
import com.example.giangnnh_basicandroid_day5.data.model.Student
import com.example.giangnnh_basicandroid_day5.databinding.FragmentBBinding
import com.example.giangnnh_basicandroid_day5.screen.main.MainActivity
import com.example.giangnnh_basicandroid_day5.viewmodel.StudentViewModel

class BFragment : Fragment(R.layout.fragment_b) {

    private lateinit var bBinding: FragmentBBinding
    private lateinit var name: String
    private lateinit var mClass: String
    private lateinit var viewModel: StudentViewModel

    private var age: Int = 0

    private var avgScore: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bBinding = FragmentBBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).studentViewModel
        return bBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).apply {
            mainBinding.buttonChangeFrame.setOnClickListener {
                if (checkInfoStudent()) {
                    supportFragmentManager.popBackStack(AFragment().javaClass.name.toString(), 0)
                    mainBinding.buttonChangeFrame.setText(R.string.text_button_add_new_student)
                    saveStudent()
                }
            }
        }
    }

    private fun getInfoStudent() {
        name = bBinding.editTextName.text.toString()
        if (bBinding.editTextAge.text.toString().isNotEmpty()) age =
            bBinding.editTextAge.text.toString().toInt()
        mClass = bBinding.editTextClass.text.toString()
        if (bBinding.editTextAvgScore.text.toString().isNotEmpty())
            avgScore = bBinding.editTextAvgScore.text.toString().toInt()
    }

    private fun checkInfoStudent(): Boolean {
        getInfoStudent()
        if (name.isEmpty() || name.length < 5) {
            showToast(getString(R.string.error_edit_text_name))
            return false
        }
        if (age <= 0 || age >= 50) {
            showToast(getString(R.string.error_edit_text_age))
            return false
        }
        if (mClass.isEmpty() || mClass.length < 5) {
            showToast(getString(R.string.error_edit_text_class))
            return false
        }
        if ((avgScore < 0) || (avgScore > 100)) {
            showToast(getString(R.string.error_edit_text_avgscore))
            return false
        }
        return true
    }

    private fun saveStudent() {
        viewModel.addStudent(Student(name, age, mClass, avgScore))
    }

    private fun showToast(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}