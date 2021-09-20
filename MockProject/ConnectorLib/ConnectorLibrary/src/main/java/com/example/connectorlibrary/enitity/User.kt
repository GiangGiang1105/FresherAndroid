package com.example.connectorlibrary.enitity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Entity(tableName = "user")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    var user_id: Int = 0,
    var name: String = "",
    var phone_number: String = "",
    var passport_number: String = "",
    var birthday: Long = 0,
    var gender_id: Int = 0,
    var email: String = "",
    var address: String = "",
    var isActive: Boolean = true
) : Parcelable