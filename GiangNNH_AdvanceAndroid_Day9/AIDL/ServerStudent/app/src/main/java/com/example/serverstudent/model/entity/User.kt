package com.example.serverstudent.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Entity(tableName = "User")
@Parcelize
data class User(@PrimaryKey(autoGenerate = true) val id : Int = 0, val name: String = "") : Parcelable