package com.example.clientmessage.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import com.example.clientmessage.MainActivity
import com.example.clientmessage.R

class IPCMessengerClient : Service() {

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val keyStop = msg.data.getInt(KEY_RECEIVE_STOP)
            if (keyStop == KEY_STOP) {
                stopMessener()
            } else {
                val msgValue = msg.data.getString(KEY_RECEIVE)
                Log.d(TAG, "Receive Message: $msgValue")
                postDelayed({
                    sendData()
                }, 5000)
            }
        }
    }

    private val messenger = Messenger(mHandler)
    private var messengerServer: Messenger? = null

    fun initMessengerServer(messenger: Messenger) {
        messengerServer = messenger
    }

    override fun onBind(intent: Intent): IBinder = messenger.binder

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onCreate() {
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

        val pendingIntent = Intent(this, IPCMessengerClient::class.java).apply {
            action = ACTION_STOP
        }.let {
            PendingIntent.getService(this, 0, it, 0)
        }

        return Notification.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_service)
            .setContentIntent(pendingIntent)
            .setContentText("IPC Client Messenger is running ... ")
            .setContentTitle("IPC Client")
            .setOngoing(true)
            .build()
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

    fun sendData() {
        val key = (0..5).random()
        val bundle = Bundle().apply {
            putInt(KEY_SEND, key)
        }
        Message.obtain().apply {
            data = bundle
            messengerServer?.send(this)
            replyTo = messenger
        }
        Log.d(TAG, "sendMessage: $key")
    }

    private fun stopMessener() {
        mHandler.removeCallbacksAndMessages(null)
        stopForeground(true)
        stopSelf()
    }

    companion object {
        private const val TAG = "IPCMessengerClient"
        private const val KEY_STOP = 100
        private const val KEY_RECEIVE = "VALUE"
        private const val KEY_RECEIVE_STOP = "STOP"
        private const val KEY_SEND = "KEY"
        private const val CHANNEL_ID = "IPC Client"
        private const val ACTION_STOP = "Stop Client"
        private const val ID_SERVICE_FOREGROUND = 10

        private var instance: IPCMessengerClient? = null

        fun getInstance(): IPCMessengerClient = instance ?: IPCMessengerClient().also {
            instance = it
        }
    }
}