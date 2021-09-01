package com.example.day7.utils

import com.example.day7.data.model.Transaction

class ValidateTransaction {

    fun testValid(holderName: String, amount: String): ValidationResult {
        if (holderName.isEmpty()) {
            return ValidationResult.Failure(Constants.ERROR_HOLDER_NAME, "Holder name is not null")
        } else if (amount.isEmpty()) {
            return ValidationResult.Failure(Constants.ERROR_AMOUNT, "Amount is not null")
        } else if (amount.toDouble() <= 0) {
            return ValidationResult.Failure(Constants.ERROR_AMOUNT, "Amount must more than 0 ")
        }
        return ValidationResult.Success
    }
}