package com.example.day7.screen.fragment

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.example.day6.base.BaseFragment
import com.example.day7.data.model.Transaction
import com.example.day7.databinding.FragmentSaleBinding
import com.example.day7.utils.*
import com.example.day7.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaleFragment : BaseFragment<FragmentSaleBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentSaleBinding =
        FragmentSaleBinding.inflate(layoutInflater)

    override fun init() {
        binding.handler = this
    }

    fun submitSale(view: View) {
        val holderName = binding.holderNameTextField.editText?.text.toString()
        val amount = binding.amountTextField.editText?.text.toString()
        val currency = (if (binding.radioUSD.isChecked) Currency.USD else Currency.VND).name
        val type = TransactionType.SALE.name
        val time = System.currentTimeMillis()
        val validate: ValidationResult = ValidateTransaction().testValid(holderName, amount)
        if (validateForm(validate)) {
            lifecycle.coroutineScope.launch {
                viewModel.insertTransaction(
                    Transaction(
                        currency = currency, holderName =
                        holderName, amount = amount.toDouble(), type = type,
                        time = time
                    )
                )
            }
            clearData()
        }
    }

    private fun validateForm(validateResult: ValidationResult): Boolean =
        when (validateResult) {
            is ValidationResult.Success -> true
            is ValidationResult.Failure -> {
                if (validateResult.holderError == Constants.ERROR_HOLDER_NAME) binding.holderNameTextField.error =
                    validateResult.errorMsg
                if (validateResult.holderError == Constants.ERROR_AMOUNT) binding.amountTextField.error =
                    validateResult.errorMsg
                false
            }
        }

    private fun clearData() {
        binding.holderNameTextField.editText?.setText("")
        binding.amountTextField.editText?.setText("")
    }
}