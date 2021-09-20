package com.example.connectorlibrary.enitity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "status")
@Parcelize
data class Status(
    @PrimaryKey(autoGenerate = true)
    var status_id: Int = 0, var status_name: String = ""
) : Parcelable