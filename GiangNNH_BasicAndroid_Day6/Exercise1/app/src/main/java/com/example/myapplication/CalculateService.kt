package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.myapplication.utils.Constant

class CalculateService : Service() {

    private val calculateBinder = CalculateBinder()
    private var resultCalculate = 0

    override fun onBind(intent: Intent): IBinder {
        return calculateBinder
    }

    fun calculator(a: Int, b: Int, messsage: String) {
        when (messsage) {
            Constant.PLUS -> resultCalculate = a + b
            Constant.MINUS -> resultCalculate = a - b
            Constant.MULTI -> resultCalculate = a * b
            Constant.DIVIDE -> {
                if (b!=0) resultCalculate = a / b
                else {
                    val intent = Intent().also {
                        it.action = Constant.ACTION_RESULT_INTENT
                        it.putExtra(Constant.BUNDLE_ERROR_DEVIDE, ERROR_DEVIDE)
                    }
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                }
            }
        }
        val intent = Intent().also {
            it.action = Constant.ACTION_RESULT_INTENT
            it.putExtra(Constant.BUNDLE_RESULT, resultCalculate)
        }
       LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    inner class CalculateBinder : Binder() {
        fun getService() = CalculateService()
    }

    companion object{
        private const val ERROR_DEVIDE = "Can't calculation! Because b is 0!"
    }
}