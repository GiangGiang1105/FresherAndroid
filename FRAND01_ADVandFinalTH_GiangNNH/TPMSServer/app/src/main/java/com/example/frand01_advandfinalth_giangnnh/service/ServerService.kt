package com.example.frand01_advandfinalth_giangnnh.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.example.frand01_advandfinalth_giangnnh.IPMSSInterface
import com.example.frand01_advandfinalth_giangnnh.R
import com.example.frand01_advandfinalth_giangnnh.data.dao.ITireDefaultDao
import com.example.frand01_advandfinalth_giangnnh.data.dao.ITireSensorDao
import com.example.frand01_advandfinalth_giangnnh.data.model.TireDefault
import com.example.frand01_advandfinalth_giangnnh.data.model.TireSensor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServerService : Service() {

    @Inject
    lateinit var tireDefaultDao: ITireDefaultDao

    @Inject
    lateinit var tireSensorDao: ITireSensorDao

    private val binder = object : IPMSSInterface.Stub() {
        override fun getTireDefaut(): TireDefault {
            return tireDefaultDao.getTireDefaut()
        }

        override fun insertTireDefault(tireDefault: TireDefault): Long {
            Log.e(TAG, "insertTireDefault: ", )
            return tireDefaultDao.insertTireDefault(tireDefault)
        }

        override fun updateTireDefault(tireDefault: TireDefault): Int =
            tireDefaultDao.updateTireDefault(tireDefault)

        override fun deleteTireDefault(tireDefault: TireDefault): Int =
            tireDefaultDao.deleteTireDefault(tireDefault)

        override fun getTireSensor(): TireSensor = tireSensorDao.getTireSensor()

        override fun insertTireSensor(tireSensor: TireSensor): Long =
            tireSensorDao.insertTireSensor(tireSensor)

        override fun updateTireSensor(tireSensor: TireSensor): Int =
            tireSensorDao.updateTireSensor(tireSensor)

        override fun deleteTireSensor(tireSensor: TireSensor): Int =
            tireSensorDao.deleteTireSensor(tireSensor)

    }

    override fun onBind(intent: Intent?): IBinder {
        Log.e(TAG, "onBind: ", )
        return binder
    }

    override fun unbindService(conn: ServiceConnection) {
        stopForeground(true)
        super.unbindService(conn)
    }

    override fun onCreate() {
       /* startForeground(ID_SERVICE_FOREGROUND, createNotification())*/
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: ", )
        return START_STICKY
    }

    private fun createNotification(): Notification {
        val name = "Channel TPMS Server"
        val description = "Channel TPMS Server"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            this.description = description
        }
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )

        return Notification.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_service)
            .setContentText("TPMS Service is running ... ")
            .setContentTitle("TPMS Service")
            .setOngoing(true)
            .build()
    }


    companion object {
        private const val TAG = "ServiceServer"
        private const val CHANNEL_ID = "TPMS Server"
        private const val ID_SERVICE_FOREGROUND = 10
    }

}