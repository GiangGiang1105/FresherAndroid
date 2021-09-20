package com.example.clientmessage.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import androidx.core.os.bundleOf
import com.example.clientmessage.Constants
import com.example.clientmessage.MainActivity
import com.example.clientmessage.R

class IPCMessengerClient : Service() {

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                Constants.START_COMMUNICATE -> {
                    sendData(msg, this)
                }
                Constants.FROM_SERVER -> {
                    val value = msg.data.getString(Constants.VALUE)
                    Log.d(TAG, "Receive value from server : $value")
                    sendData(msg, this)
                }
                Constants.STOP_CLIENT -> {
                    stopClientMessener()
                }
            }
        }
    }

    private val messenger = Messenger(mHandler)

    override fun onBind(intent: Intent): IBinder = messenger.binder

    override fun onCreate() {
        startForeground(ID_SERVICE_FOREGROUND, createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopClientMessener()
            }
            else -> throw IllegalArgumentException("No action!!!")
        }
        return START_STICKY
    }

    fun sendData(msg: Message, handler: Handler) {
        val mServer = msg.replyTo
        val key = (0..5).random()
        val message = Message.obtain().apply {
            data = bundleOf(Constants.KEY to key)
            what = Constants.TO_SERVER
            replyTo = messenger
        }
        handler.postDelayed({ mServer.send(message) }, 5000)
        Log.d(TAG, "Send to server: $key")
    }

    private fun stopClientMessener() {
        mHandler.removeCallbacksAndMessages(null)
        stopForeground(true)
        stopSelf()
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

    companion object {
        private const val TAG = "IPC Client"
        private const val CHANNEL_ID = "IPC Client"
        private const val ACTION_STOP = "Stop Client"
        private const val ID_SERVICE_FOREGROUND = 10
    }
}