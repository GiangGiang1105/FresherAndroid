package com.example.tpmsclient.data.datasource

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.example.frand01_advandfinalth_giangnnh.IPMSSInterface
import com.example.frand01_advandfinalth_giangnnh.data.model.TireDefault
import com.example.frand01_advandfinalth_giangnnh.data.model.TireSensor

class TireDataSource(private val context: Context) {

    private val intentService = Intent(ACTION).apply {
        setClassName(PACKAGE_NAME, CLASS_NAME)
    }
    private var binder: IPMSSInterface? = null
    private var isBinder = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = IPMSSInterface.Stub.asInterface(service)
            Log.e(TAG, "onServiceConnected: ", )
            isBinder = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
            isBinder = false
        }

    }

    fun connectServer() {
        Log.e(TAG, "connectServer: ", )
        if (isBinder) {
            context.bindService(intentService, connection, Context.BIND_AUTO_CREATE)
        }
    }

    fun getTireDefault(): TireDefault? {
        if (isBinder) return binder?.tireDefaut as TireDefault
        return null
    }

    fun getTireSensor(): TireSensor? {
        if (isBinder) return binder?.tireSensor as TireSensor
        return null
    }

    fun insertTireDefault(tireDefault: TireDefault): Long? {
        if (isBinder) return binder?.insertTireDefault(tireDefault)
        else throw NullPointerException("Binder is null")
    }

    fun insertTireSensor(tireSensor: TireSensor): Long? {
        if (isBinder) return binder?.insertTireSensor(tireSensor)
        else throw NullPointerException("Binder is null")
    }

    fun updateTireDefault(tireDefault: TireDefault): Int? {
        if (isBinder) return binder?.updateTireDefault(tireDefault)
        else throw NullPointerException("Binder is null")
    }

    fun updateTireSensor(tireSensor: TireSensor): Int? {
        if (isBinder) return binder?.updateTireSensor(tireSensor)
        else throw NullPointerException("Binder is null")
    }

    fun deleteTireDefault(tireDefault: TireDefault): Int? {
        if (isBinder) return binder?.deleteTireDefault(tireDefault)
        else throw NullPointerException("Binder is null")
    }

    fun deleteTireSensor(tireSensor: TireSensor): Int? {
        if (isBinder) return binder?.deleteTireSensor(tireSensor)
        else throw NullPointerException("Binder is null")
    }

    fun stopServerService() {
        context.unbindService(connection)
        context.stopService(intentService)
    }

    companion object {
        private const val ACTION =
            "com.example.frand01_advandfinalth_giangnnh.service.CONNECT_SERVER_TMPS"
        private const val PACKAGE_NAME = "com.example.frand01_advandfinalth_giangnnh"
        private const val CLASS_NAME =
            "com.example.frand01_advandfinalth_giangnnh.service.ServerService"
        private val TAG = "TireDataSource"
    }
}