package com.example.day6.screen.authentication.fragment

import android.view.LayoutInflater
import com.example.day6.R
import com.example.day6.base.BaseFragment
import com.example.day6.databinding.FragmentProfileInfoBinding
import com.google.android.material.datepicker.MaterialDatePicker


class ProfileInfoFragment : BaseFragment<FragmentProfileInfoBinding>() {

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentProfileInfoBinding =
        FragmentProfileInfoBinding.inflate(layoutInflater)

    override fun init() {
        setDatePicker()
        binding.buttonContinue.setOnClickListener {
            val action = ProfileInfoFragmentDirections.actionProfileInfoFragment2ToProfilePictureFragment2()
            controller.navigate(action)
        }
    }

    private fun setDatePicker() {
        val materialDatePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText(R.string.text_birthday)
                .setTheme(R.style.ThemeOverlay_App_DatePicker)
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
        val picker = materialDatePicker.build()
        binding.dateTextField.setOnClickListener {
            picker.show(parentFragmentManager, "PICKER_BIRTHDAY")
        }
        picker.addOnPositiveButtonClickListener {
            binding.dateTextField.setText(picker.headerText)
        }
    }
}