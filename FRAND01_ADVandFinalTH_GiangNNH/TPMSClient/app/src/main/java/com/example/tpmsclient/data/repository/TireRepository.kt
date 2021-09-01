package com.example.tpmsclient.data.repository

import android.content.Context
import com.example.frand01_advandfinalth_giangnnh.data.model.TireDefault
import com.example.frand01_advandfinalth_giangnnh.data.model.TireSensor
import com.example.tpmsclient.base.BaseRepository
import com.example.tpmsclient.data.datasource.TireDataSource
import javax.inject.Inject

class TireRepository @Inject constructor(private val tireDataSource: TireDataSource) :
    BaseRepository() {

    fun connectServer() {
        tireDataSource.connectServer()
    }

    suspend fun getTireDefault() = safeApi {
        tireDataSource.getTireDefault()
    }

    suspend fun getTireSensor() = safeApi {
        tireDataSource.getTireSensor()
    }

    suspend fun insertTireDefault(tireDefault: TireDefault) = safeApi {
        tireDataSource.insertTireDefault(tireDefault)
    }

    suspend fun insertTireSensor(tireSensor: TireSensor) = safeApi {
        tireDataSource.insertTireSensor(tireSensor)
    }


    suspend fun updateTireDefault(tireDefault: TireDefault) = safeApi {
        tireDataSource.updateTireDefault(tireDefault)
    }

    suspend fun updateTireSensor(tireSensor: TireSensor) = safeApi {
        tireDataSource.updateTireSensor(tireSensor)
    }

    suspend fun deleteTireDefault(tireDefault: TireDefault) = safeApi {
        tireDataSource.deleteTireDefault(tireDefault)
    }

    suspend fun deleteTireSensor(tireSensor: TireSensor) = safeApi {
        tireDataSource.deleteTireSensor(tireSensor)
    }

    fun stopServerService() {
        tireDataSource.stopServerService()
    }
}