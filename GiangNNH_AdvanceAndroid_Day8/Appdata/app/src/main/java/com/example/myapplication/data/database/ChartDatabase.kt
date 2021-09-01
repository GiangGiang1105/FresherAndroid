package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.ChartDao
import com.example.myapplication.data.model.ColumnChart
import com.example.myapplication.data.model.PieChart

@Database(entities = [PieChart::class, ColumnChart::class], exportSchema = true, version = 1)
abstract class ChartDatabase : RoomDatabase() {
    abstract fun getChartItemDao(): ChartDao
}