package com.example.exercise2

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.exercise2.databinding.ActivityMainBinding
import com.example.exercise2.utils.Constant


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val broadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            handleCallBackBroadCast(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadcastReceiver, IntentFilter(Constant.ACTION_DOWNLOAD))
        mainBinding.buttonDownload.setOnClickListener {
            startService(Intent(this, DownloadService::class.java).putExtra(Constant.URL, URL))
            mainBinding.downloadProcess.isVisible = true
        }
        setContentView(mainBinding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    @SuppressLint("SetTextI18n")
    private fun handleCallBackBroadCast(intent: Intent?) {
        intent?.let {
            if (it.hasExtra(Constant.EXTRA_PROCESS)) {
                if (it.getStringExtra(Constant.EXTRA_PROCESS).equals(Constant.PROCESS_START)) {
                    mainBinding.textProcess.text = "Downloading..."
                }
                if (it.getStringExtra(Constant.EXTRA_PROCESS).equals(Constant.PROCESS_DONE)) {
                    mainBinding.downloadProcess.isVisible = false
                    mainBinding.textProcess.text = "Done!"
                }
            }
            if (it.hasExtra(Constant.EXTRA_ERROR_DOWNLOAD)) {
                Toast.makeText(
                    this,
                    it.getStringExtra(Constant.EXTRA_ERROR_DOWNLOAD),
                    Toast.LENGTH_LONG
                ).show()
            }
            if (it.hasExtra(Constant.EXTRAS_BITMAP)) {
                val uriBitmap =
                    it.getSerializableExtra(Constant.EXTRAS_BITMAP) as UriBitMap
                mainBinding.imageDownload.setImageURI(uriBitmap.uri)
            }
        }
    }

    companion object {
        private val URL =
            "https://bloganh.net/wp-content/uploads/2021/03/chup-anh-dep-anh-sang-min.jpg"
    }
}