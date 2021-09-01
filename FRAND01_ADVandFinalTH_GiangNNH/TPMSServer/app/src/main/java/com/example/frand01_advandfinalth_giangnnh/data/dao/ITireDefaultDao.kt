package com.example.frand01_advandfinalth_giangnnh.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.frand01_advandfinalth_giangnnh.data.model.TireDefault

@Dao
interface ITireDefaultDao {

    @Query("SELECT * FROM TireDefault ORDER BY id DESC LIMIT 1")
    fun getTireDefaut(): TireDefault

    @Insert
    fun insertTireDefault(tireDefault: TireDefault): Long

    @Update
    fun updateTireDefault(tireDefault: TireDefault): Int

    @Update
    fun deleteTireDefault(tireDefault: TireDefault): Int
}