package com.example.clientmessage.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import com.example.clientmessage.R

class IPCMessengerServer : Service() {

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val keyStop = msg.data.getInt(KEY_RECEIVE_STOP)
            if (keyStop == KEY_STOP) stopMessener()
            else {
                val msgKey = msg.data.getInt(KEY_RECEIVE)
                Log.d(TAG, " Receive Message:$msgKey ")
                var msgValue: String = handleKeyToValue(msgKey)
                replyMessage(msg, msgValue)
            }
        }
    }

    private val messager = Messenger(mHandler)

    override fun onBind(intent: Intent): IBinder {
        return messager.binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopMessener()
            }
            else -> throw IllegalArgumentException("No action!!!")
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(ID_SERVICE_FOREGROUND, createNotification())
    }

    private fun createNotification(): Notification {
        val name = "Channel IPC"
        val description = "Channel IPC Messager"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            this.description = description
        }
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )

        val pendingIntent = Intent(this, IPCMessengerServer::class.java).apply {
            action = ACTION_STOP
        }.let {
            PendingIntent.getService(this, 0, it, 0)
        }

        return Notification.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_service)
            .setContentIntent(pendingIntent)
            .setContentText("IPC Server Messenger is running ... ")
            .setContentTitle("IPC Server")
            .setOngoing(true)
            .build()
    }

    private fun handleKeyToValue(key: Int): String = when (key) {
        0 -> "Window"
        1 -> "Linux"
        2 -> "Mac"
        3 -> "IOS"
        4 -> "Unix"
        5 -> "Android"
        else -> throw IllegalArgumentException("No key")
    }

    private fun replyMessage(message: Message, value: String) {
        val bundle = Bundle().apply {
            putString(KEY_REPLY, value)
        }
        Message.obtain(message).apply {
            data = bundle
            message.replyTo.send(this)
        }
        Log.d(TAG, "replyMessage: $value")
    }

    private fun stopMessener() {
        mHandler.removeCallbacksAndMessages(null)
        stopForeground(true)
        stopSelf()
        Log.e(TAG, "stopMessener: ", )
    }

    companion object {
        private const val TAG = "IPCMessagerService"
        private const val CHANNEL_ID = "IPC Server"
        private const val ACTION_STOP = "Stop Server"
        private const val ID_SERVICE_FOREGROUND = 5
        private const val KEY_STOP = 100
        private const val KEY_RECEIVE = "KEY"
        private const val KEY_REPLY = "VALUE"
        private const val KEY_RECEIVE_STOP = "STOP"
    }
}