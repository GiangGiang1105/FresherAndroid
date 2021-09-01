package com.example.day7.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.day7.data.model.Transaction
import com.example.day7.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TransactionRepository) :
    ViewModel() {

    val transaction = repository.getAllTransactions()

    suspend fun insertTransaction(transaction: Transaction) =
        repository.insertTransaction(transaction)

    suspend fun clearDatabase() = repository.clearDatabase()

    suspend fun updateTypeTransaction(transaction: Transaction) =
        repository.updateTypeTransaction(transaction)

    fun getFetchTransactionsByHolderName(holderName: String) =
        repository.getTransactionsByHolderName(holderName)
}