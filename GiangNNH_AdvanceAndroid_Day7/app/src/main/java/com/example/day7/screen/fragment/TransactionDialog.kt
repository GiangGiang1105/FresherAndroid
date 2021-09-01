package com.example.day7.screen.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.day7.data.model.Transaction
import com.example.day7.databinding.CustomDialogBinding
import com.example.day7.screen.activity.main.MainActivity
import com.example.day7.utils.TransactionType
import com.example.day7.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class TransactionDialog : DialogFragment() {

    private var transaction: Transaction? = null
    private val viewModel: MainViewModel by lazy {
        (activity as MainActivity).viewModel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = activity?.let {
        val builder = AlertDialog.Builder(it)
        val inflater = requireActivity().layoutInflater
        val customDialogBinding = CustomDialogBinding.inflate(inflater)
        customDialogBinding.cardView.background.alpha = 200
        customDialogBinding.transaction = transaction
        customDialogBinding.handler = this
        builder.setView(customDialogBinding.root)
        builder.create()
    } ?: throw IllegalStateException("Activity can be null ")


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            transaction = it.getSerializable(KEY_TRANSACTION) as Transaction
        }
    }

    fun onCancel(view: View) {
        dismiss()
    }

    fun onRefund(view: View) {
        transaction?.type = TransactionType.REFUND.name
        lifecycleScope.launch {
            transaction?.let { viewModel.updateTypeTransaction(it) }
        }
        dismiss()
    }

    companion object {

        private const val KEY_TRANSACTION = "KEY_TRANSACTION"

        fun newInstance(transaction: Transaction): DialogFragment {
            val args = Bundle()
            args.putSerializable(KEY_TRANSACTION, transaction)
            return TransactionDialog().apply {
                arguments = args
            }
        }
    }
}