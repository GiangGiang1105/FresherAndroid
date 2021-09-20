package com.example.connectorlibrary.enitity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PeopleInDay(var day: Long = 0, var people: Int = 0) : Parcelable