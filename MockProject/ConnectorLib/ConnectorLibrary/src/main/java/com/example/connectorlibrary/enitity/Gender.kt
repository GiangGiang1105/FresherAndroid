package com.example.connectorlibrary.enitity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "gender")
@Parcelize
class Gender(
    @PrimaryKey(autoGenerate = true)
    var gender_id: Int = 0,
    var gender_name: String = ""
) : Parcelable