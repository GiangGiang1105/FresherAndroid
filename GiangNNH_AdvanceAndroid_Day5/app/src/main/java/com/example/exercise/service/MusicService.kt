package com.example.exercise.service

import android.annotation.SuppressLint
import android.app.*
import android.content.*
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleService
import com.bumptech.glide.Glide
import com.example.exercise.R
import com.example.exercise.base.BaseLifecyle
import com.example.exercise.screen.main.MainActivity
import com.example.exercise.service.media.MediaManager
import com.example.exercise.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MusicService : LifecycleService() {

    private val mediaManager by lazy { MediaManager.getInstance(applicationContext) }
    private lateinit var notification: NotificationCompat.Builder
    private lateinit var remoteViews: RemoteViews

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                Constants.ACTION_SEND_DATA -> {
                    val title = intent.getStringExtra(Constants.KEY_TITLE_SONG)
                    val image = intent.getStringExtra(Constants.KEY_IMAGE_SONG)
                    remoteViews.setTextViewText(R.id.titleTextView, title)
                    context?.let {
                        GlobalScope.launch(Dispatchers.IO) {
                            val bitmap = Glide.with(context)
                                .asBitmap()
                                .load(image)
                                .submit(120, 120)
                                .get()
                            withContext(Dispatchers.Main) {
                                remoteViews.setImageViewBitmap(R.id.notificationImageView, bitmap)
                            }
                        }
                    }
                    startForeground(idService, notification.build())
                }
                Constants.ACTION_PLAY_PAUSE -> {
                    if (mediaManager.mediaPlayer.isPlaying) {
                        remoteViews.setImageViewResource(R.id.pauseImageButton, R.drawable.ic_play)
                        mediaManager.stop()
                    } else {
                        remoteViews.setImageViewResource(
                            R.id.pauseImageButton,
                            R.drawable.ic_pause
                        )
                        mediaManager.playASong(true)
                    }
                    startForeground(idService, notification.build())
                }
                Constants.ACTION_PREV -> {
                    mediaManager.prevASong()
                }
                Constants.ACTION_NEXT -> {
                    mediaManager.nextASong()
                }
                Constants.ACTION_STOP_SERVICE -> {
                    stopService(Intent(context, MusicService::class.java))
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        lifecycle.addObserver(BaseLifecyle(mediaManager))
        init()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        createNotificationMusic()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadCastReceiver)
    }

    private fun init() {
        initReceiver()
        createNotificationChannel()
    }

    private fun initReceiver() {
        IntentFilter().apply {
            addAction(Constants.ACTION_STOP_SERVICE)
            addAction(Constants.ACTION_NEXT)
            addAction(Constants.ACTION_PREV)
            addAction(Constants.ACTION_PLAY_PAUSE)
            addAction(Constants.ACTION_SEND_DATA)
            registerReceiver(broadCastReceiver, this)
        }
    }

    private fun createNotificationMusic() {
        val pendingIntent = Intent(this, MainActivity::class.java).let {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            PendingIntent.getActivity(this, 0, it, 0)
        }
        remoteViews = RemoteViews(packageName, R.layout.layout_notification_music)
        remoteViews.apply {
            setOnClickPendingIntent(
                R.id.nextImageButton,
                createPendingIntent(Constants.ACTION_NEXT)
            )
            setOnClickPendingIntent(
                R.id.previousImageButton,
                createPendingIntent(Constants.ACTION_PREV)
            )
            setOnClickPendingIntent(
                R.id.pauseImageButton,
                createPendingIntent(Constants.ACTION_PLAY_PAUSE)
            )
            setOnClickPendingIntent(
                R.id.closeImageButton,
                createPendingIntent(Constants.ACTION_STOP_SERVICE)
            )
        }

        notification = NotificationCompat.Builder(this, channelId).apply {

            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setContentIntent(pendingIntent)
            setContent(remoteViews)
            setSmallIcon(R.drawable.ic_notification)

            priority = NotificationCompat.PRIORITY_HIGH
            setOngoing(true)
            setOnlyAlertOnce(true)
            setGroupSummary(true)
        }

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification.build())
        startForeground(idService, notification.build())
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chan = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_NONE
            )
            chan.lightColor = ContextCompat.getColor(this@MusicService, R.color.color_338102)
            chan.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val manager = (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            manager.createNotificationChannel(chan)
        }
    }

    private fun createPendingIntent(actionIntent: String): PendingIntent =
        Intent().let {
            it.action = actionIntent
            PendingIntent.getBroadcast(this@MusicService, 0, it, 0)
        }

    companion object {
        const val channelId = "com.example.app.Music"
        private const val channelName = "My music Service"
        private const val idService = 1
    }
}
