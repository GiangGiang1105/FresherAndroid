package com.example.clientaidl.screen.fragment

import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextSwitcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.navArgs
import com.example.clientaidl.R
import com.example.clientaidl.base.BaseFragment
import com.example.clientaidl.databinding.FragmentUpdateBinding
import com.example.clientaidl.utils.Resource
import com.example.clientaidl.utils.ValidateForm
import com.example.serveraidl.data.model.Student
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : BaseFragment<FragmentUpdateBinding>() {

    private val args: UpdateFragmentArgs by navArgs()
    private lateinit var student: Student

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentUpdateBinding =
        FragmentUpdateBinding.inflate(layoutInflater)

    override fun init() {
        listenerResultUpdate()
        handleValidate()
        student = args.student
        binding.student = student
        binding.buttonUpdate.setOnClickListener {
            buildAlertUpdate()
        }
    }

    private fun updateStudent() {
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
            viewModel.updateStudent(
                Student(
                    id = student.id,
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

    private fun listenerResultUpdate() {
        viewModel.resultUpdate.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { result ->
                        if (result != -1) {
                            showToast(getString(R.string.mess_update_success))
                            val action =
                                UpdateFragmentDirections.actionUpdateFragmentToStudentsFragment()
                            controller.navigate(action)
                        } else showToast(getString(R.string.mess_update_unsuccess))
                    } ?: run {
                        Log.d(TAG, "listenerResultDelete: result update null")
                    }
                }
                is Resource.Error -> Log.e(TAG, "listenerResultUpdate: ${it.exception} ")
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
        private const val TAG = "UpdateFragment"
    }
}