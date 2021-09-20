package com.example.connectorlibrary.controller

import android.content.Context
import com.example.connectorlibrary.base.CallbackProvider
import com.example.connectorlibrary.callback.CallbackConnector
import com.example.connectorlibrary.enitity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServiceControllerAdmin(context: Context) : CallbackConnector.CallbackConnectorAdmin,
    CallbackProvider<CallbackConnector.CallbackConnectorAdmin> {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val serviceProvider = ServiceConnector.Builder(context)
        .setCallback(this)
        .build()

    override val callbacks: ArrayList<CallbackConnector.CallbackConnectorAdmin> = ArrayList()


    override fun addCallback(callback: CallbackConnector.CallbackConnectorAdmin) {
        super.addCallback(callback)
        if (callbacks.size > 0) {
            connect()
        }
    }

    override fun removeCallback(callback: CallbackConnector.CallbackConnectorAdmin) {
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
            callbacks.forEach {
                it.onFailureResponse(failureResponse)
            }
        }
    }

    override fun onGetAllUsers(listUsersResponse: ListUsersResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onGetAllUsers(listUsersResponse)
            }
        }
    }

    override fun onDeleteUser(user: UserResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onDeleteUser(user)
            }
        }
    }

    override fun onLockUser(userResponse: UserResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onLockUser(userResponse)
            }
        }
    }

    private fun connect() {
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

    fun getAllUsers() {
        serviceProvider.getAllUsers()
    }

    fun deleteUser(user: User) {
        serviceProvider.deleteUser(user)
    }

    fun lockUser(user: User) {
        serviceProvider.lockUser(user)
    }
}