package com.example.excercise1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyAppOnStartWhenSreenOn : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_SCREEN_ON.equals(intent.action)) {
            Intent(context, MainActivity::class.java).also {
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(it)
            }
        }
    }
}