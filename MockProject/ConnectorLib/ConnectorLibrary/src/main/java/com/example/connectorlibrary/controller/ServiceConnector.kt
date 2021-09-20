package com.example.connectorlibrary.controller

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.example.connectorlibrary.R
import com.example.connectorlibrary.callback.CallbackConnector
import com.example.connectorlibrary.enitity.*

class ServiceConnector(private val context: Context) {

    var serviceConnected = false
    private var serverService: IServerService? = null
    var callbackConnector: CallbackConnector? = null
    var callbackConnectorAdmin: CallbackConnector.CallbackConnectorAdmin? = null
    var callbackConnectorUser: CallbackConnector.CallbackConnectorUser? = null


    private val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected")
            serviceConnected = true
            serverService = IServerService.Stub.asInterface(service)
            try {
                serverService?.registerCallback(serverServiceCallback)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            callbackConnector?.onServerConnected()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected")
            serverService = null
            serviceConnected = false
        }

    }

    private val serverServiceCallback = object : IServerServiceCallback.Stub() {

        override fun onGetUserHealths(listHealths: HealthResponse) {
            callbackConnector?.onGetUserHealths(listHealths)
        }

        override fun onGetStatus(statusResponse: StatusResponse) {
            callbackConnector?.onGetStatus(statusResponse)
        }

        override fun onGetSymptom(symptomResponse: SymptomResponse) {
            callbackConnector?.onGetSymptom(symptomResponse)
        }

        override fun onGetGender(genderResponse: GenderResponse) {
            callbackConnector?.onGetGender(genderResponse)
        }

        override fun onFailureResponse(failureResponse: FailureResponse) {
            callbackConnector?.onFailureResponse(failureResponse)
        }

        override fun onUserSignUp(authResponse: AuthResponse) {
            callbackConnectorUser?.onUserSignUp(authResponse)
        }

        override fun onUserSignIn(authResponse: AuthResponse) {
            callbackConnectorUser?.onUserSignIn(authResponse)
        }

        override fun onInsertHealth(healthResponse: HealthResponse) {
            callbackConnectorUser?.onInsertHealth(healthResponse)
        }

        override fun onGetUserInformation(userResponse: UserResponse) {
            callbackConnectorUser?.onGetUserInformation(userResponse)
        }

        override fun onUpdateUser(userResponse: UserResponse) {
            callbackConnectorUser?.onUpdateUser(userResponse)
        }

        override fun onGetHistoryCovidVn(history: HistoryCovidResponse) {
            callbackConnectorUser?.onGetHistoryCovidVn(history)
        }

        override fun onGetHistoryCovidWorld(history: HistoryCovidResponse) {
            callbackConnectorUser?.onGetHistoryCovidWorld(history)
        }

        override fun onGetAllUsers(listUsersResponse: ListUsersResponse) {
            callbackConnectorAdmin?.onGetAllUsers(listUsersResponse)
        }

        override fun onDeleteUser(userResponse: UserResponse) {
            callbackConnectorAdmin?.onDeleteUser(userResponse)
        }

        override fun onLockUser(userResponse: UserResponse) {
            callbackConnectorAdmin?.onLockUser(userResponse)
        }

    }

    fun connectService() {
        Log.e(TAG, "connectService: ")
        if (serviceConnected) {
            Log.d(TAG, "connectService: service was already connected. Ignoring...")
            return
        }
        val intent = Intent()
        intent.apply {
            component =
                ComponentName.unflattenFromString(context.getString(R.string.component_server_service))
            action = context.getString(R.string.action_server_service)
        }
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun disconnectService() {
        Log.e(TAG, "disconnectService: ")
        if (!serviceConnected) {
            Log.d(TAG, "disconnectService:  service is not connected. Ignoring...")
            return
        }
        try {
            serverService?.unregisterCallback(serverServiceCallback)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        context.unbindService(serviceConnection)
        serviceConnected = false
    }

    fun getStatus() {
        if (!serviceConnected) {
            Log.d(TAG, "getStatus: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.getStatus()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun getSymptom() {
        if (!serviceConnected) {
            Log.d(TAG, "getSymptom: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.getSymptom()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun getGender() {
        if (!serviceConnected) {
            Log.d(TAG, "getGender: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.getGender()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun getUserHealths() {
        if (!serviceConnected) {
            Log.d(TAG, "getUserHealths: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.getUserHealths()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun getAllUsers() {
        if (!serviceConnected) {
            Log.d(TAG, "getAllUsers: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.getAllUsers()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun deleteUser(user: User) {
        if (!serviceConnected) {
            Log.d(TAG, "getAllUsers: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.deleteUser(user)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun lockUser(user: User) {
        if (!serviceConnected) {
            Log.d(TAG, "lockUser: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.lockUser(user)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun userSignUp(user: User) {
        if (!serviceConnected) {
            Log.d(TAG, "userSignUp: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            if (serverService == null) {
                Log.e(TAG, "userSignUp: 1")
            }
            serverService?.userSignUp(user)
            Log.e(TAG, "userSignUp: ")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun userSignIn(phoneNumber: String) {
        if (!serviceConnected) {
            Log.d(TAG, "userSignIn: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.userSignIn(phoneNumber)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun insertHealth(health: Health) {
        if (!serviceConnected) {
            Log.d(TAG, "insertHealth: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.insertHealth(health)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun getHistoryCovidWorld() {
        if (!serviceConnected) {
            Log.d(TAG, "getStatisticCovid: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.getHistoryCovidWorld()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun getHistoryCovidVn() {
        if (!serviceConnected) {
            Log.d(TAG, "getStatisticCovid: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.getHistoryCovidVn()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun getUserInformation(userId: Int) {
        if (!serviceConnected) {
            Log.d(TAG, "getUser: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.getUserInformation(userId)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun updateUser(user: User) {
        if (!serviceConnected) {
            Log.d(TAG, "updateUser: service is not connected. Ignoring...")
            throw RemoteException("Service is not connected")
        }
        try {
            serverService?.updateUser(user)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    class Builder(context: Context) {

        private val connectorClient = ServiceConnector(context)

        fun setCallback(cb: CallbackConnector): Builder {
            connectorClient.callbackConnector = cb
            if (cb is CallbackConnector.CallbackConnectorUser) connectorClient.callbackConnectorUser =
                cb
            if (cb is CallbackConnector.CallbackConnectorAdmin) connectorClient.callbackConnectorAdmin =
                cb
            return this@Builder
        }

        fun build(): ServiceConnector = connectorClient
    }

    companion object {
        val TAG: String = ServiceConnector::class.java.name
    }
}


