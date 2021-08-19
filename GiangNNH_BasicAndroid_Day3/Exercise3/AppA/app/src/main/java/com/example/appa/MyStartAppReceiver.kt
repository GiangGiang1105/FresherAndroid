package com.example.appa

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyStartAppReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            context.startActivity(this)
        }
    }
}