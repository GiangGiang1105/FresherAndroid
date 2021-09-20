package com.example.clientstudent.app

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClientApplication : Application() {

    companion object {

        val TAG: String = ClientApplication::class.java.name

        fun logDebug(messenger: String) {
            Log.d(TAG, messenger)
        }

        fun logError(messenger: String) {
            Log.e(TAG, messenger)
        }

        fun showToast(context: Context, messenger: String) {
            Toast.makeText(context, messenger, Toast.LENGTH_LONG).show()
        }
    }
}