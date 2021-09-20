package com.example.clientaidl.utils

import android.content.Context
import com.example.clientstudent.R
import com.google.android.material.textfield.TextInputLayout

class ValidateForm(private val context: Context) {

    var isValidate = true
        private set

    fun validateName(textInputLayout: TextInputLayout) {
        val name = textInputLayout.editText?.text.toString()
        if (name.equals("")) {
            textInputLayout.error = context.getString(R.string.name_null)
            isValidate = false
        } else if (name.length < 10) {
            textInputLayout.error = context.getString(R.string.name_lenght)
            isValidate = false
        } else textInputLayout.helperText = context.getString(R.string.valid_success)
    }

    fun validateAge(textInputLayout: TextInputLayout) {
        val age = textInputLayout.editText?.text.toString()
        if (age.equals("")) {
            textInputLayout.error = context.getString(R.string.age_null)
            isValidate = false
        } else if (age.toInt() <= 10 || age.toInt() >= 30) {
            textInputLayout.error = context.getString(R.string.age_error)
            isValidate = false
        } else textInputLayout.helperText = context.getString(R.string.valid_success)
    }

    fun validateScore(vararg textInputLayout: TextInputLayout) {
        textInputLayout.forEach {
            val score = it.editText?.text.toString()
            if (score.equals("")) {
                it.error = context.getString(R.string.score_null)
                isValidate = false
            } else if (score.toFloat() < 0 || score.toFloat() > 100) {
                it.error = context.getString(R.string.score_error)
                isValidate = false
            } else it.helperText = context.getString(R.string.valid_success)
        }
    }
}