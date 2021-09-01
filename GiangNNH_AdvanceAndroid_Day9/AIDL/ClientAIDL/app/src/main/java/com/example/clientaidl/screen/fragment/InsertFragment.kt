package com.example.clientaidl.screen.fragment

import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.clientaidl.R
import com.example.clientaidl.base.BaseFragment
import com.example.clientaidl.databinding.FragmentInsertBinding
import com.example.clientaidl.utils.Resource
import com.example.clientaidl.utils.ValidateForm
import com.example.serveraidl.data.model.Student
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertFragment : BaseFragment<FragmentInsertBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentInsertBinding =
        FragmentInsertBinding.inflate(layoutInflater)

    override fun init() {
        binding.buttonAdd.setOnClickListener {
            addStudent()
        }
        listenerResultInsert()
        handleValidate()
    }

    private fun addStudent() {
        var validateForm = ValidateForm(requireContext())
        val name = binding.nameTextField.editText?.text.toString()
        val age = binding.ageStudentTextField.editText?.text.toString()
        val math = binding.mathTextField.editText?.text.toString()
        val english = binding.englishTextField.editText?.text.toString()
        val physical = binding.physicalTextField.editText?.text.toString()
        val chemistry = binding.chemistryTextField.editText?.text.toString()
        val literature = binding.literatureTextField.editText?.text.toString()
        val isValidName = validateForm.validateName(name, binding.nameTextField)
        val isValidAge = validateForm.validateAge(age, binding.ageStudentTextField)
        val isValidChemistry = validateForm.validateScore(chemistry, binding.chemistryTextField)
        val isValidMath = validateForm.validateScore(math, binding.mathTextField)
        val isValidEnglish = validateForm.validateScore(english, binding.englishTextField)
        val isValidPhysical = validateForm.validateScore(physical, binding.physicalTextField)
        val isValidLiterature = validateForm.validateScore(literature, binding.literatureTextField)
        if (isValidAge && isValidName && isValidChemistry && isValidEnglish && isValidMath && isValidLiterature && isValidPhysical) {
            viewModel.insertStudent(
                Student(
                    name = name,
                    age = age.toInt(),
                    math = math.toFloat(),
                    english = english.toFloat(),
                    physical = physical.toFloat(),
                    chemistry = chemistry.toFloat(),
                    literature = literature.toFloat()
                )
            )
            clearHelperText()
        }
    }

    private fun handleValidate() {
        binding.nameTextField.editText?.apply {
            setOnFocusChangeListener { _, _ ->
                clearError()
            }
        }
        binding.ageStudentTextField.editText?.apply {
            setOnFocusChangeListener { _, _ ->
                clearError()
            }
        }
        binding.chemistryTextField.editText?.apply {
            setOnFocusChangeListener { _, _ ->
                clearError()
            }
        }
        binding.mathTextField.editText?.apply {
            setOnFocusChangeListener { _, _ ->
                clearError()
            }
        }
        binding.englishTextField.editText?.apply {
            setOnFocusChangeListener { _, _ ->
                clearError()
            }
        }
        binding.physicalTextField.editText?.apply {
            setOnFocusChangeListener { _, _ ->
                clearError()
            }
        }
        binding.literatureTextField.editText?.apply {
            setOnFocusChangeListener { _, _ ->
                clearError()
            }
        }
    }

    private fun clearError() {
        binding.nameTextField.error = null
        binding.ageStudentTextField.error = null
        binding.mathTextField.error = null
        binding.physicalTextField.error = null
        binding.englishTextField.error = null
        binding.literatureTextField.error = null
        binding.chemistryTextField.error = null
    }

    private fun clearHelperText() {
        binding.nameTextField.helperText = null
        binding.ageStudentTextField.helperText = null
        binding.mathTextField.helperText = null
        binding.physicalTextField.helperText = null
        binding.englishTextField.helperText = null
        binding.literatureTextField.helperText = null
        binding.chemistryTextField.helperText = null
    }

    private fun clearInput() {
        binding.nameTextField.editText?.setText("")
        binding.ageStudentTextField.editText?.setText("")
        binding.mathTextField.editText?.setText("")
        binding.englishTextField.editText?.setText("")
        binding.physicalTextField.editText?.setText("")
        binding.chemistryTextField.editText?.setText("")
        binding.literatureTextField.editText?.setText("")
    }

    private fun listenerResultInsert() {
        viewModel.resultInsert.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { result ->
                        if (result > -1) {
                            showToast(getString(R.string.insert_success))
                            clearInput()
                        } else showToast(getString(R.string.insert_error))
                    } ?: run {
                        Log.d(TAG, "listenerResultDelete: result insert null")
                    }
                }
                is Resource.Error -> Log.e(TAG, "listenerResultInsert: ${it.exception} ")
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
        private const val TAG = "InsertFragment"
    }
}