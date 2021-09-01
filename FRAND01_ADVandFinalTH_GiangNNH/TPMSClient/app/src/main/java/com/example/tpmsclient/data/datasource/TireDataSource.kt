package com.example.tpmsclient.data.datasource

import android.content.Context
import android.content.Intent

class TireDataSource(private val context: Context) {

    private val intentService = Intent(ACTION).apply {

    }

    companion object {
        private const val ACTION =
            "com.example.frand01_advandfinalth_giangnnh.service.CONNECT_SERVER_TMPS"
        private const val PACKAGE_NAME = "com.example.frand01_advandfinalth_giangnnh"
        private const val CLASS_NAME =
            "com.example.frand01_advandfinalth_giangnnh.service.ServerService"
    }
}