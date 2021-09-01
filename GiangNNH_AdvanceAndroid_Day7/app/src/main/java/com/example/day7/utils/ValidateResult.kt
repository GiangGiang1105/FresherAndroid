package com.example.day7.utils

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Failure(val holderError: String, val errorMsg: String) : ValidationResult()
}