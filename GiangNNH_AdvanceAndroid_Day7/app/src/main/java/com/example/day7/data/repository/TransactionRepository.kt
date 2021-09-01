package com.example.day7.data.repository

import com.example.day7.data.database.dao.TransactionDao
import com.example.day7.data.model.Transaction
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val transactionDao: TransactionDao) {

    fun getAllTransactions() = transactionDao.getAllTransaction()

    fun getTransactionsByHolderName(holderName: String) =
        transactionDao.getTransactionByHolderName(holderName)

    suspend fun insertTransaction(transaction: Transaction) =
        transactionDao.insertTransaction(transaction)

    suspend fun clearDatabase() = transactionDao.clearDatabase()

    suspend fun updateTypeTransaction(transaction: Transaction) =
        transactionDao.updateTypeTransaction(transaction)
}