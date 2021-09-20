package com.example.appgraph.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        private val TAG = MyApplication::class.java.name

        fun logCatDebug(message: String) {
            Log.d(TAG, message)
        }

        fun logCatError(error: String) {
            Log.d(TAG, error)
        }
    }
}