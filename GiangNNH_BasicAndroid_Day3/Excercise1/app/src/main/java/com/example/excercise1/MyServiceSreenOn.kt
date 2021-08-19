package com.example.excercise1

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log

class MyServiceSreenOn : Service() {

    private var intentFilter: IntentFilter? = null
    private val myAppOnStartWhenSreenOn by lazy { MyAppOnStartWhenSreenOn() }

    override fun onCreate() {
        super.onCreate()
        intentFilter = IntentFilter().apply {
            addAction("android.intent.action.SCREEN_ON")
            priority = 100
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        registerReceiver(myAppOnStartWhenSreenOn, intentFilter)
        return START_STICKY
    }

    override fun onDestroy() {
        unregisterReceiver(myAppOnStartWhenSreenOn)
        super.onDestroy()
        Log.e("hihihiih", "HIHIHII")
    }
}