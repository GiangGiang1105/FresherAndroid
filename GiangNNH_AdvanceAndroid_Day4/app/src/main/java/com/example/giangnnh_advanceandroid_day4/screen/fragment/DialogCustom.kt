package com.example.giangnnh_advanceandroid_day4.screen.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.giangnnh_advanceandroid_day4.data.model.Category
import com.example.giangnnh_advanceandroid_day4.databinding.ItemCategoryBinding

class DialogCustom : DialogFragment() {

    private var category: Category? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = activity?.let {
        val builder = AlertDialog.Builder(it)
        val inflater = requireActivity().layoutInflater
        val itemCategoryBinding = ItemCategoryBinding.inflate(inflater)
        itemCategoryBinding.category = category
        itemCategoryBinding.bool = true
        builder.setView(itemCategoryBinding.root)
            .setPositiveButton("OK") { _, _ ->
                dismiss()
            }
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
            category = it.getSerializable(KEY_CATEGORY) as Category
        }
    }

    companion object {

        private const val KEY_CATEGORY = "KEY_CATEGORY"

        fun newInstance(category: Category): DialogFragment {
            val args = Bundle()
            args.putSerializable(KEY_CATEGORY, category)
            return DialogCustom().apply {
                arguments = args
            }
        }
    }
}