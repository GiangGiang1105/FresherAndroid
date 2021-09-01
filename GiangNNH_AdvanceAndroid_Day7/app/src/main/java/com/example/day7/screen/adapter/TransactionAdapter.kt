package com.example.day7.screen.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.day7.data.model.Transaction
import com.example.day7.databinding.ItemTransactionBinding

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    private var onItemCLickListener: ((transition: Transaction) -> Unit)? = null
    private var _listTransactions = mutableListOf<Transaction>()

    fun updateListTransaction(newListTransaction: List<Transaction>) {
        newListTransaction?.let {
            _listTransactions.clear()
            _listTransactions.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun setOnItemClickListener(listener: (transition: Transaction) -> Unit) {
        onItemCLickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder =
        TransactionHolder(
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        holder.bindData(transaction = _listTransactions[position])
    }

    override fun getItemCount(): Int = _listTransactions.size

    inner class TransactionHolder(private val itemTransactionBinding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(itemTransactionBinding.root) {

        fun bindData(transaction: Transaction) {
            itemTransactionBinding.transaction = transaction
            itemTransactionBinding.onClickListener = onItemCLickListener
        }
    }
}