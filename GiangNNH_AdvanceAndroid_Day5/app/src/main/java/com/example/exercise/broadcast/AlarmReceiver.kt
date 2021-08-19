package com.example.exercise.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.exercise.service.MusicService
import com.example.exercise.utils.Constants

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Constants.ACTION_SET_ALARM -> {
                Intent(context, MusicService::class.java).apply {
                    context.startService(this)
                }
            }
        }
    }
}