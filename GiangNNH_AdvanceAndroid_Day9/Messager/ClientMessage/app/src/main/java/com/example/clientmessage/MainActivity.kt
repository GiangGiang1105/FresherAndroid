package com.example.clientmessage

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clientmessage.databinding.ActivityMainBinding
import com.example.clientmessage.service.IPCMessengerClient
import com.example.clientmessage.service.IPCMessengerServer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var intentServer: Intent

    private lateinit var intentClient: Intent

    private var mClient: Messenger? = null
    private var mServer: Messenger? = null

    private var isStartServer = false
    private var isStartClient = false
    private var isCommunicate = false

    private val connectionServer = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mServer = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mServer = null 
        }

    }

    private val connectionClient = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mClient = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mClient = null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        intentServer =
            Intent(this, IPCMessengerServer::class.java)
        intentClient = Intent(this, IPCMessengerClient::class.java)
        binding.apply {
            startServer.setOnClickListener {
                handleServer()
            }
            startCommunication.setOnClickListener {
                handleCommunication()
            }
            startClient.setOnClickListener {
                handleClient()
            }
        }
        setContentView(binding.root)
    }

    private fun handleServer() {
        if (isStartServer) {
            stopServer()
        } else {
            bindService(intentServer, connectionServer, Context.BIND_AUTO_CREATE)
            binding.startServer.text = getString(R.string.text_button_stop_server)
        }
        isStartServer = !isStartServer
    }

    private fun handleClient() {
        if (isStartClient) {
            stopClient()
        } else {
            bindService(intentClient, connectionClient, Context.BIND_AUTO_CREATE)
            binding.startClient.text = getString(R.string.text_button_stop_client)
        }
        isStartClient = !isStartClient
    }

    private fun stopServer() {
        val message = Message.obtain().apply {
            what = Constants.STOP_SERVER
        }
        mServer?.send(message)
        unbindService(connectionServer)
        stopService(intentServer)
        binding.startServer.text = getString(R.string.text_button_start_server)
    }

    private fun stopClient() {
        val message = Message.obtain().apply {
            what = Constants.STOP_CLIENT
        }
        mClient?.send(message)
        unbindService(connectionClient)
        stopService(intentClient)
        binding.startClient.text = getString(R.string.text_button_start_client)
    }

    private fun handleCommunication() {
        if (isCommunicate) {
            if (isStartServer) stopServer()
            if (isStartClient) stopClient()
            binding.startCommunication.text = getString(R.string.text_button_start_communication)
        } else {
            if (mClient != null && mServer != null) {
                val msg = Message.obtain().apply {
                    what = Constants.START_COMMUNICATE
                    replyTo = mServer
                }
                mClient!!.send(msg)
            } else {
                Toast.makeText(
                    this,
                    "Please start client and start server!!!!!!",
                    Toast.LENGTH_LONG
                ).show()
            }
            binding.startCommunication.text = getString(R.string.text_button_stop_communication)
        }
        isCommunicate = !isCommunicate
    }
}