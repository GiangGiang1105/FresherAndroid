package com.example.connectorlibrary.enitity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.connectorlibrary.base.ListPeopleConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@TypeConverters(ListPeopleConverter::class)
@Entity(tableName = "history_covid")
data class HistoryCovid(
    @PrimaryKey(autoGenerate = true)
    var history_id: Int = 0,
    var area: String = "all",
    var status: Int = 0,
    @ColumnInfo(name = "list_people_in_day")
    var listPeopleInDay: List<PeopleInDay>
) : Parcelable