package com.example.myapplication

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mService: CalculateService
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as CalculateService.CalculateBinder
            mService = binder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        Intent(this, CalculateService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }

        addFragment(mainBinding.frameA.id, CalculateFragment())
        addFragment(mainBinding.frameA.id, AFragment())
        setContentView(mainBinding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

    private fun addFragment(container: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            add(container, fragment, null)
            addToBackStack(fragment.javaClass.name.toString())
            commit()
        }
        supportFragmentManager.executePendingTransactions()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}