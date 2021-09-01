package com.example.day7.data.model

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "transaction")
class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var type: String,
    val currency: String,
    val amount: Double,
    val holderName: String,
    val time: Long,
) : Serializable {

    @SuppressLint("SimpleDateFormat")
    fun getDateTime(): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy/MM/dd")
        return format.format(date)
    }
}