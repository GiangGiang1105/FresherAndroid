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

        override fun onServiceDisconnected(name: ComponentName?) {}

    }

    private val connectionClient = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mClient = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {}

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        intentServer =
            Intent(this, IPCMessengerServer::class.java)
        intentClient = Intent(this, IPCMessengerClient::class.java)
        binding.startServer.setOnClickListener {
            handleServer()
        }
        binding.startCommunication.setOnClickListener {
            handleCommunication()
        }
        binding.startClient.setOnClickListener {
            handleClient()
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
        val bundle = Bundle().apply {
            putInt(KEY_SEND_STOP, KEY_STOP)
        }
        val message = Message.obtain().apply {
            data = bundle
        }
        mServer?.send(message)
        unbindService(connectionServer)
        stopService(intentServer)
        Log.d(TAG, "sendMessenger STOP SERVER : $KEY_STOP")
        binding.startServer.text = getString(R.string.text_button_start_server)
    }

    private fun stopClient() {
        val bundle = Bundle().apply {
            putInt(KEY_SEND_STOP, KEY_STOP)
        }
        val message = Message.obtain().apply {
            data = bundle
        }
        mClient?.send(message)
        unbindService(connectionClient)
        stopService(intentClient)
        Log.d(TAG, "sendMessenger STOP CLIENT : $KEY_STOP")
        binding.startClient.text = getString(R.string.text_button_start_client)
    }

    private fun handleCommunication() {
        if (isCommunicate) {
            if (isStartServer) stopServer()
            if (isStartClient) stopClient()
            binding.startCommunication.text = getString(R.string.text_button_start_communication)
        } else {
            if (mClient != null && mServer != null) {
                mServer?.let {
                    IPCMessengerClient.getInstance().initMessengerServer(it)
                }
                IPCMessengerClient.getInstance().sendData()
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

    companion object {
        private const val TAG = "MainActivity-Client"
        private const val KEY_STOP = 100
        private const val KEY_SEND_STOP = "STOP"
    }
}