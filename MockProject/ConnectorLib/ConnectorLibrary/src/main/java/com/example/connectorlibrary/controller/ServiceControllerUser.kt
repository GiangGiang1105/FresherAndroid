package com.example.connectorlibrary.controller

import android.content.Context
import android.util.Log
import com.example.connectorlibrary.base.CallbackProvider
import com.example.connectorlibrary.callback.CallbackConnector
import com.example.connectorlibrary.enitity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServiceControllerUser(context: Context) : CallbackConnector.CallbackConnectorUser,
    CallbackProvider<CallbackConnector.CallbackConnectorUser> {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val serviceProvider = ServiceConnector.Builder(context)
        .setCallback(this)
        .build()
    override val callbacks: ArrayList<CallbackConnector.CallbackConnectorUser> = ArrayList()

    override fun addCallback(callback: CallbackConnector.CallbackConnectorUser) {
        super.addCallback(callback)
        Log.e("TAG", "addCallback: ")
        if (callbacks.size > 0) {
            connect()
        }
    }

    override fun removeCallback(callback: CallbackConnector.CallbackConnectorUser) {
        super.removeCallback(callback)
        if (callbacks.size == 0) {
            disconnect()
        }
    }


    override fun onServerConnected() {
        coroutineScope.launch {
            callbacks.forEach {
                it.onServerConnected()
            }
        }
    }

    override fun onGetStatus(statusResponse: StatusResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onGetStatus(statusResponse)
            }
        }
    }

    override fun onGetSymptom(symptomResponse: SymptomResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onGetSymptom(symptomResponse)
            }
        }
    }

    override fun onGetGender(genderResponse: GenderResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onGetGender(genderResponse)
            }
        }
    }

    override fun onGetUserHealths(healthResponse: HealthResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onGetUserHealths(healthResponse)
            }
        }
    }

    override fun onFailureResponse(failureResponse: FailureResponse) {
        coroutineScope.launch {
            Log.e("TAG", "onFailureResponse:controller ")
            callbacks.forEach {
                it.onFailureResponse(failureResponse)
            }
        }
    }

    override fun onUserSignUp(authResponse: AuthResponse) {
        coroutineScope.launch {
            Log.e("TAG", "onUserSignUp: controller")
            callbacks.forEach {
                it.onUserSignUp(authResponse)
            }
        }
    }

    override fun onUserSignIn(authResponse: AuthResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onUserSignIn(authResponse)
            }
        }
    }

    override fun onInsertHealth(healthResponse: HealthResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onInsertHealth(healthResponse)
            }
        }
    }

    override fun onGetHistoryCovidWorld(historyCovidResponse: HistoryCovidResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onGetHistoryCovidWorld(historyCovidResponse)
            }
        }
    }

    override fun onGetHistoryCovidVn(historyCovidResponse: HistoryCovidResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onGetHistoryCovidVn(historyCovidResponse)
            }
        }
    }

    override fun onGetUserInformation(user: UserResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onGetUserInformation(user)
            }
        }
    }

    override fun onUpdateUser(user: UserResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onUpdateUser(user)
            }
        }
    }

    private fun connect() {
        Log.e("TAG", "connect: ")
        serviceProvider.connectService()
    }

    private fun disconnect() {
        serviceProvider.disconnectService()
    }

    fun getStatus() {
        serviceProvider.getStatus()
    }

    fun getSymptom() {
        serviceProvider.getSymptom()
    }

    fun getGender() {
        serviceProvider.getGender()
    }

    fun getUserHealths() {
        serviceProvider.getUserHealths()
    }

    fun userSignUp(user: User) {
        Log.e("TAG", "userSignUp: 11111")
        serviceProvider.userSignUp(user)
    }

    fun userSignIn(phoneNumber: String) {
        serviceProvider.userSignIn(phoneNumber)
    }

    fun insertHealth(health: Health) {
        serviceProvider.insertHealth(health)
    }

    fun getHistoryCovidVn() {
        serviceProvider.getHistoryCovidVn()
    }

    fun getHistoryCovidWorld() {
        serviceProvider.getHistoryCovidWorld()
    }


    fun getUserInformation(userId: Int) {
        serviceProvider.getUserInformation(userId)
    }

    fun updateUser(user: User) {
        serviceProvider.updateUser(user)
    }
}