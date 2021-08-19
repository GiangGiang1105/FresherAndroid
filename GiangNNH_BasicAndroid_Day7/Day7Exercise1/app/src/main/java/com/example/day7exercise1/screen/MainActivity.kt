package com.example.day7exercise1.screen

import android.content.*
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.day7exercise1.R
import com.example.day7exercise1.data.ListSmsData
import com.example.day7exercise1.data.SMSData
import com.example.day7exercise1.databinding.ActivityMainBinding
import com.example.day7exercise1.screen.adapter.SmsAdapter
import com.example.day7exercise1.service.LoadSmsService
import com.example.day7exercise1.utils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var mService: LoadSmsService
    private lateinit var mainBinding: ActivityMainBinding
    private val smsAdapter by lazy { SmsAdapter() }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                if (it.hasExtra(Constants.EXTRA_RESULT_SMS)) {
                    (it.getSerializableExtra(Constants.EXTRA_RESULT_SMS) as List<SMSData>).let { list ->
                        smsAdapter.updateData(list)
                    }
                }
            }
        }
    }

    private val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = (service as LoadSmsService.LoadSmsBinder).getLoadSmsService()

            checkPermissionReadSMS()
        }

        override fun onServiceDisconnected(name: ComponentName?) {}

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        bindService(
            Intent(this, LoadSmsService::class.java),
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )

        initView()
        setContentView(mainBinding.root)
    }

    override fun onStart() {
        super.onStart()
        this.registerReceiver(broadcastReceiver, IntentFilter(Constants.ACTION_RESULT_SMS))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
        unbindService(serviceConnection)
        stopService(Intent(this, LoadSmsService::class.java))
    }

    private fun checkPermissionReadSMS() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_SMS
            ) == PackageManager.PERMISSION_GRANTED
            -> {
                mService.getAllSms(context = this)
            }
            shouldShowRequestPermissionRationale(android.Manifest.permission.READ_SMS) -> {
                showAlertDialogWhenUserDiniedRequestReadSMS(getString(R.string.message_retionable_request_permission_read_sms))
            }
            else -> {
                requestPermissionReadSMS()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.REQUEST_CODE_PERMISSION_READ_SMS -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mService.getAllSms(this)
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.message_deny_forever_permission),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else -> {
                Toast.makeText(this, getString(R.string.text_error), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initView() {
        mainBinding.smsRecyclerView.apply {
            setHasFixedSize(false)
            adapter = smsAdapter
        }
    }

    private fun requestPermissionReadSMS() {
        requestPermissions(
            arrayOf(android.Manifest.permission.READ_SMS),
            Constants.REQUEST_CODE_PERMISSION_READ_SMS
        )
    }

    private fun showAlertDialogWhenUserDiniedRequestReadSMS(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage(message)
            //uwf //ok/ba la ..hahah 
            setPositiveButton(
                getString(R.string.allow_request_permission)
            ) { _, _ ->
                requestPermissionReadSMS()
            }
            setNegativeButton(getString(R.string.deny_request_permission)) { _, _ ->
                finish()
            }
            create()
        }
        val alert = builder.create()
        alert.setTitle("Notification!!!!")
        alert.show()
    }
}

