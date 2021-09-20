package com.example.connectorlibrary.base

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListSymptomConverter {
    @TypeConverter
    fun listToString(value: List<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun stringToList(value: String) =
        Gson().fromJson(value, Array<Int>::class.java).toList()
}