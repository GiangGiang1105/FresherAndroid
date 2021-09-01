package com.example.clientaidl.utils

import android.content.Context
import android.util.Log
import com.google.android.material.textfield.TextInputLayout
import com.example.clientaidl.R

class ValidateForm(private val context: Context) {

    fun validateName(name: String, textInputLayout: TextInputLayout): Boolean {
        if (name.isEmpty()) {
            textInputLayout.error = context.getString(R.string.name_null)
            return false
        }
        if (name.length < 10) {
            textInputLayout.error = context.getString(R.string.name_lenght)
            return false
        }
        textInputLayout.helperText = context.getString(R.string.valid_success)
        return true
    }

    fun validateAge(age: String, textInputLayout: TextInputLayout): Boolean {
        if (age.isEmpty()) {
            textInputLayout.error = context.getString(R.string.age_null)
            return false
        }
        if (age.toInt() <= 10 || age.toInt() >= 30) {
            textInputLayout.error = context.getString(R.string.age_error)
            return false
        }
        textInputLayout.helperText = context.getString(R.string.valid_success)
        return true
    }

    fun validateScore(score: String, textInputLayout: TextInputLayout): Boolean {
        if (score.isEmpty()) {
            textInputLayout.error = context.getString(R.string.score_null)
            return false
        }
        if (score.toFloat() <0 || score.toFloat() >100) {
            textInputLayout.error = context.getString(R.string.score_error)
            Log.e("TAG", "validateScore: ", )
            return false
        }
        textInputLayout.helperText = context.getString(R.string.valid_success)
        return true
    }
}