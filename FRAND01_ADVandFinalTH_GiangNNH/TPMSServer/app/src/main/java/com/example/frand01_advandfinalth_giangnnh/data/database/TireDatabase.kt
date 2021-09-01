package com.example.frand01_advandfinalth_giangnnh.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.frand01_advandfinalth_giangnnh.data.dao.ITireDefaultDao
import com.example.frand01_advandfinalth_giangnnh.data.dao.ITireSensorDao
import com.example.frand01_advandfinalth_giangnnh.data.model.TireDefault
import com.example.frand01_advandfinalth_giangnnh.data.model.TireSensor

@Database(entities = [TireSensor::class, TireDefault::class], exportSchema = true, version = 1)
abstract class TireDatabase : RoomDatabase() {

    abstract fun getTireDefaultDao(): ITireDefaultDao

    abstract fun getTireSensorDao(): ITireSensorDao
}