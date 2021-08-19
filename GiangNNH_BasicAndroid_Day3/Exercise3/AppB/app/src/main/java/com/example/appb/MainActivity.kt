package com.example.appb

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStartAppAByIntent.setOnClickListener {
            startActivity(intentStartAppA())
        }

        buttonStartAppAByBroadCast.setOnClickListener {
            sendBroadcast(intentStartAppByBroadCast())
        }
    }

    private fun intentStartAppA() = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, editTextMessage.text.toString())
    }

    private fun intentStartAppByBroadCast() = Intent().apply {
        action = "test.action.MYSTARTAPPRECEIVER"
        putExtra(Intent.EXTRA_TEXT, editTextMessage.text.toString())
        component = ComponentName("com.example.appa", "com.example.appa.MyStartAppReceiver")
    }
}