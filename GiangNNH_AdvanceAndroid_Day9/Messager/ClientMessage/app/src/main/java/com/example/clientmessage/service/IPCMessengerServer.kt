package com.example.clientmessage.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.core.os.bundleOf
import com.example.clientmessage.Constants
import com.example.clientmessage.R

class IPCMessengerServer : Service() {

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                Constants.FROM_CLIENT -> {
                    val key = msg.data.getInt(Constants.KEY)
                    Log.d(TAG, " Receive key from : $key")
                    val value: String = handleKeyToValue(key)
                    replyMessage(msg, value)
                }
                Constants.STOP_SERVER -> {
                    stopServerMessener()
                }
            }
        }
    }

    private val messenger = Messenger(mHandler)

    override fun onBind(intent: Intent): IBinder = messenger.binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopServerMessener()
            }
            else -> throw IllegalArgumentException("No action!!!")
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(ID_SERVICE_FOREGROUND, createNotification())
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
        Message.obtain().apply {
            data = bundleOf(Constants.VALUE to value)
            what = Constants.TO_CLIENT
            replyTo = messenger
            message.replyTo.send(this)
        }
        Log.d(TAG, "Reply to client: $value")
    }

    private fun stopServerMessener() {
        mHandler.removeCallbacksAndMessages(null)
        stopForeground(true)
        stopSelf()
        Log.e(TAG, "Stop server !")
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


    companion object {
        private const val TAG = "IPC Server"
        private const val CHANNEL_ID = "IPC Server"
        private const val ACTION_STOP = "Stop Server"
        private const val ID_SERVICE_FOREGROUND = 5
    }
}