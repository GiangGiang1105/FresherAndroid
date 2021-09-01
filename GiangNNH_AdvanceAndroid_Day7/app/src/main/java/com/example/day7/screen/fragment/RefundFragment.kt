package com.example.day7.screen.fragment

import android.util.Log
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.day6.base.BaseFragment
import com.example.day7.databinding.FragmentRefundBinding
import com.example.day7.screen.adapter.TransactionAdapter
import com.example.day7.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RefundFragment : BaseFragment<FragmentRefundBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private val transactionAdapter by lazy { TransactionAdapter() }

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentRefundBinding =
        FragmentRefundBinding.inflate(layoutInflater)

    override fun init() {
        initView()
        searchTransactions()
        onItemClickListener()
    }

    private fun initView() {
        binding.resultRecyclerView.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun onItemClickListener() {
        transactionAdapter.setOnItemClickListener {
            TransactionDialog.newInstance(it).show(parentFragmentManager, "Transaction Dialog")
        }
    }

    private fun searchTransactions() {
        binding.searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getFetchTransactionsByHolderName(query.toString())
                    .observe(viewLifecycleOwner, {
                        transactionAdapter.updateListTransaction(it)
                    })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getFetchTransactionsByHolderName(newText.toString())
                    .observe(viewLifecycleOwner, {
                        transactionAdapter.updateListTransaction(it)
                    })
                return false
            }

        })
    }
}