package com.example.day7exercise1.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.provider.Telephony
import android.util.Log
import com.example.day7exercise1.data.ListSmsData
import com.example.day7exercise1.data.SMSData
import com.example.day7exercise1.utils.Constants
import java.io.Serializable

class LoadSmsService : Service() {

    private var _binder: LoadSmsBinder = LoadSmsBinder()
    private lateinit var _body: String
    private lateinit var _creator: String
    private var _listSms: MutableList<SMSData> = mutableListOf()

    override fun onBind(intent: Intent): IBinder {
        return _binder
    }


    inner class LoadSmsBinder : Binder() {

        fun getLoadSmsService(): LoadSmsService = LoadSmsService()
    }

    fun getAllSms(context: Context) {
        val cursor = context.contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            arrayOf(
                        Telephony.Sms.BODY,
                Telephony.Sms.CREATOR
            ),
            null, null, null
        )
        if (cursor!!.count > 0) {
            while (cursor.moveToNext()) {
                _body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY))
                _creator = cursor.getString(cursor.getColumnIndex(Telephony.Sms.CREATOR))
                _listSms.add(SMSData(body = _body, creator = _creator))
            }
        }

        context.sendBroadcast(Intent().apply {
            action = Constants.ACTION_RESULT_SMS
            putExtra(Constants.EXTRA_RESULT_SMS, _listSms as Serializable)
        })
    }
}