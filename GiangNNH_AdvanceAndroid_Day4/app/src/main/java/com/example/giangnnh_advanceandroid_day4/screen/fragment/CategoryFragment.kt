package com.example.giangnnh_advanceandroid_day4.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giangnnh_advanceandroid_day4.R
import com.example.giangnnh_advanceandroid_day4.databinding.FragmentCategoryBinding
import com.example.giangnnh_advanceandroid_day4.screen.activity.MainActivity
import com.example.giangnnh_advanceandroid_day4.screen.adapter.CategoryAdapter
import com.example.giangnnh_advanceandroid_day4.utils.showDialog
import com.example.giangnnh_advanceandroid_day4.utils.showToast

class CategoryFragment : Fragment() {


    private lateinit var categoryBinding: FragmentCategoryBinding
    private val categoryAdapter by lazy {
        CategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        initView()
        updateData()
        handleOnItemOnClick()
        return categoryBinding.root
    }

    private fun initView() {
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        categoryBinding.recyclerView.apply {
            setHasFixedSize(false)
            adapter = categoryAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun updateData() {
        (activity as MainActivity).viewModel.apply {
            category.observe(requireActivity(), {
                categoryAdapter.updateListCategories(it.categories)
            })
            error.observe(requireActivity(), {
                if (it.isNetworkError) showToast(getString(R.string.error_network))
                if (it.errorCode != null) showToast(getString(R.string.error_code + it.errorCode))
                if (it.response != null) showToast(it.response.toString())
            })
        }
    }

    private fun handleOnItemOnClick() {
        categoryAdapter.setOnItemClickListener {
            showDialog(it)
        }
    }
}