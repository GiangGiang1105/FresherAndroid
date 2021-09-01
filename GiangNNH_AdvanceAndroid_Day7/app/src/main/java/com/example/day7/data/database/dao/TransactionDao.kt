package com.example.day7.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.day7.data.model.Transaction

@Dao
interface TransactionDao {

    @Query("SELECT * from `transaction`")
    fun getAllTransaction(): LiveData<List<Transaction>>

    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("delete from `transaction`")
    suspend fun clearDatabase()

    @Query("SELECT * from `transaction` WHERE holderName LIKE '%' || :holderName || '%'")
    fun getTransactionByHolderName(holderName: String): LiveData<List<Transaction>>

    @Update
    suspend fun updateTypeTransaction(transaction: Transaction)
}