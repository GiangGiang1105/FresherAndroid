package com.example.connectorlibrary.base

import androidx.room.TypeConverter
import com.example.connectorlibrary.enitity.PeopleInDay
import com.google.gson.Gson

class ListPeopleConverter {
    @TypeConverter
    fun listToJsonString(value: List<PeopleInDay>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) =
        Gson().fromJson(value, Array<PeopleInDay>::class.java).toList()
}