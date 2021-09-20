package com.example.serveraidl.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ServerApplication : Application() {

    companion object {
        val TAG: String = ServerApplication::class.java.name

        fun logDebug(messenger: String) {
            Log.d(TAG, messenger)
        }

        fun logError(messenger: String) {
            Log.e(TAG, messenger)
        }
    }
}