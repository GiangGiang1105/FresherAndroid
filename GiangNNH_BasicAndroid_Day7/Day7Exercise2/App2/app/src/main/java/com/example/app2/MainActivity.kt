package com.example.app2

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermissionReadSMS()

    }

    private fun checkPermissionReadSMS() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                "com.example.app1.permission.HAPPY_SUCCESSFUL"
            ) == PackageManager.PERMISSION_GRANTED
            -> {
                Intent().apply {
                    component = ComponentName("com.example.app1", "com.example.app1.MainActivity")
                    startActivity(this)
                }
            }
            shouldShowRequestPermissionRationale(android.Manifest.permission.READ_SMS) -> {
                requestPermissionReadSMS()
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
            101 -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent().apply {
                        component =
                            ComponentName("com.example.app1", "com.example.app1.MainActivity")
                        startActivity(this)
                    }
                } else {
                    Toast.makeText(
                        this,
                        "You not yet granted permission for that app ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun requestPermissionReadSMS() {
        requestPermissions(
            arrayOf("com.example.app1.permission.HAPPY_SUCCESSFUL"),
            101
        )
    }
}