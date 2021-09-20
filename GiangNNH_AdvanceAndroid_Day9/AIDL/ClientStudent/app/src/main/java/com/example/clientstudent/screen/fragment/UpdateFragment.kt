package com.example.clientstudent.screen.fragment

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.navigation.fragment.navArgs
import com.example.aidllibrary.entity.Student
import com.example.clientstudent.base.BaseFragment
import com.example.clientaidl.utils.ValidateForm
import com.example.clientstudent.R
import com.example.clientstudent.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : BaseFragment<FragmentUpdateBinding>() {

    private val args: UpdateFragmentArgs by navArgs()
    private lateinit var studentUpdate: Student

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentUpdateBinding =
        FragmentUpdateBinding.inflate(layoutInflater)

    override fun init() {
        handleClearValidate()
        studentUpdate = args.student
        binding.student = studentUpdate
        binding.buttonUpdate.setOnClickListener {
            buildAlertUpdate()
        }
    }

    private fun updateStudent() {
        var validateForm = ValidateForm(requireContext())
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
                    viewModel.updateStudent(
                        Student(
                            id = studentUpdate.id,
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
                    controller.navigate(UpdateFragmentDirections.actionUpdateFragmentToStudentsFragment())
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

    private fun buildAlertUpdate() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.let {
            it.setMessage(R.string.dialog_message_update)
            it.setTitle(R.string.dialog_title_update)
            it.setPositiveButton(R.string.posi_yes) { dialog, _ ->
                updateStudent()
                dialog.dismiss()
            }
            it.setNegativeButton(R.string.nav_no) { dialog, _ ->
                dialog.dismiss()
            }
            val alert = it.create()
            alert.show()
        }
    }

}