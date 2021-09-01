package com.example.frand01_advandfinalth_giangnnh.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.frand01_advandfinalth_giangnnh.data.model.TireSensor

@Dao
interface ITireSensorDao {

    @Query("SELECT * FROM TireSensor ORDER BY id DESC LIMIT 1")
    fun getTireSensor(): TireSensor

    @Insert
    fun insertTireSensor(tireSensor: TireSensor): Long

    @Update
    fun updateTireSensor(tireSensor: TireSensor): Int

    @Update
    fun deleteTireSensor(tireSensor: TireSensor): Int
}