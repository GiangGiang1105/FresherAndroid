package com.example.day7.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.day7.data.database.dao.TransactionDao
import com.example.day7.data.model.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}