package com.example.day7.screen.fragment

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.day6.base.BaseFragment
import com.example.day7.data.model.Transaction
import com.example.day7.databinding.FragmentRevenueBinding
import com.example.day7.screen.adapter.TransactionAdapter
import com.example.day7.utils.Currency
import com.example.day7.utils.TransactionType
import com.example.day7.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RevenueFragment : BaseFragment<FragmentRevenueBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private val transactionAdapter by lazy { TransactionAdapter() }
    private val caseFilter = listOf("All", "Sale", "Refund", "USD", "VND")
    private var listTransaction = mutableListOf<Transaction>()

    override fun getViewBinding(layoutInflater: LayoutInflater): FragmentRevenueBinding =
        FragmentRevenueBinding.inflate(layoutInflater)

    override fun init() {
        binding.listAllRecyclerView.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
        initData()
        initSpinner()
        filter()
    }

    private fun initSpinner() {
        val adapterSpinner =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, caseFilter)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.filterSpinner.adapter = adapterSpinner
    }

    private fun filter() {
        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0-> {
                        transactionAdapter.updateListTransaction(listTransaction)
                    }
                    1 -> {
                        listTransaction.filter {
                            it.type == TransactionType.SALE.name
                        }.let {
                            transactionAdapter.updateListTransaction(it)
                        }
                    }
                    2 -> {
                        listTransaction.filter {
                            it.type == TransactionType.REFUND.name
                        }.let {
                            transactionAdapter.updateListTransaction(it)
                        }
                    }
                    3 -> {
                        listTransaction.filter {
                            it.currency == Currency.USD.name
                        }.let {
                            transactionAdapter.updateListTransaction(it)
                        }
                    }
                    4 -> {
                        listTransaction.filter {
                            it.currency == Currency.VND.name
                        }.let {
                            transactionAdapter.updateListTransaction(it)
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

    private fun initData() {
        viewModel.transaction.observe(viewLifecycleOwner, {
            listTransaction.addAll(it)
            transactionAdapter.updateListTransaction(listTransaction)
        })
    }
}