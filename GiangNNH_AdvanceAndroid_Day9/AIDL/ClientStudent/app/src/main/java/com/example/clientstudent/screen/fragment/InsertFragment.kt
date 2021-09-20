package com.example.clientstudent.screen.fragment

import android.view.LayoutInflater
import com.example.aidllibrary.entity.Student
import com.example.clientstudent.base.BaseFragment
import com.example.clientaidl.utils.ValidateForm
import com.example.clientstudent.databinding.FragmentInsertBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertFragment : BaseFragment<FragmentInsertBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentInsertBinding =
        FragmentInsertBinding.inflate(layoutInflater)

    override fun init() {
        binding.buttonAdd.setOnClickListener {
            addStudent()
        }
        handleClearValidate()
    }

    private fun addStudent() {
        val validateForm = ValidateForm(requireContext())
        binding.apply {
            val name = nameTextField.editText?.text.toString()
            val age = ageStudentTextField.editText?.text.toString()
            val math = mathTextField.editText?.text.toString()
            val english = englishTextField.editText?.text.toString()
            val physical = physicalTextField.editText?.text.toString()
            val chemistry = chemistryTextField.editText?.text.toString()
            val literature = literatureTextField.editText?.text.toString()


            validateForm.apply {
                validateName(nameTextField)
                validateAge(ageStudentTextField)
                validateScore(
                    chemistryTextField,
                    mathTextField,
                    englishTextField,
                    physicalTextField,
                    literatureTextField
                )
                if (isValidate) {
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
                    clearValidate()
                    clearInput()
                }
            }
        }
    }

    private fun handleClearValidate() {
        binding.apply {
            nameTextField.editText?.apply {
                setOnFocusChangeListener { _, _ ->
                    clearValidate()
                }
            }
            ageStudentTextField.editText?.apply {
                setOnFocusChangeListener { _, _ ->
                    clearValidate()
                }
            }
            chemistryTextField.editText?.apply {
                setOnFocusChangeListener { _, _ ->
                    clearValidate()
                }
            }
            mathTextField.editText?.apply {
                setOnFocusChangeListener { _, _ ->
                    clearValidate()
                }
            }
            englishTextField.editText?.apply {
                setOnFocusChangeListener { _, _ ->
                    clearValidate()
                }
            }
            physicalTextField.editText?.apply {
                setOnFocusChangeListener { _, _ ->
                    clearValidate()
                }
            }
            literatureTextField.editText?.apply {
                setOnFocusChangeListener { _, _ ->
                    clearValidate()
                }
            }
        }
    }

    private fun clearValidate() {
        binding.apply {
            ageStudentTextField.apply {
                error = null
                helperText = null
            }
            nameTextField.apply {
                error = null
                helperText = null
            }
            mathTextField.apply {
                error = null
                helperText = null
            }
            physicalTextField.apply {
                error = null
                helperText = null
            }
            englishTextField.apply {
                error = null
                helperText = null
            }
            literatureTextField.apply {
                error = null
                helperText = null
            }
            chemistryTextField.apply {
                error = null
                helperText = null
            }
        }
    }

    private fun clearInput() {
        binding.apply {
            nameTextField.editText?.setText("")
            ageStudentTextField.editText?.setText("")
            mathTextField.editText?.setText("")
            englishTextField.editText?.setText("")
            physicalTextField.editText?.setText("")
            chemistryTextField.editText?.setText("")
            literatureTextField.editText?.setText("")
        }
    }
}