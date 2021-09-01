package com.example.myapplication.data.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.model.ColumnChart
import com.example.myapplication.data.model.PieChart
import com.example.myapplication.provider.ChartContract

@Dao
interface ChartDao {

    @Insert
    fun insertPieChart(chartItem: PieChart?): Long

    @Query("SELECT * FROM " + ChartContract.PieChartEntry.TABLE_NAME)
    fun getAllPieChartItem(): Cursor

    @Update
    fun updatePieChart(chartItem: PieChart?): Int

    @Insert
   fun insertColumnChart(chartItem: ColumnChart?): Long

    @Query("SELECT * FROM " + ChartContract.ColumnChartEntry.TABLE_NAME)
    fun getAllColumnChartItem(): Cursor

    @Update
    fun updateColumnChart(chartItem: ColumnChart?): Int

}