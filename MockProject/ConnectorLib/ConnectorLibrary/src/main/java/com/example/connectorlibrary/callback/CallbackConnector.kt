package com.example.connectorlibrary.callback

import com.example.connectorlibrary.enitity.*

interface CallbackConnector {

    fun onServerConnected()

    fun onGetStatus(statusResponse: StatusResponse)

    fun onGetSymptom(symptomResponse: SymptomResponse)

    fun onGetGender(genderResponse: GenderResponse)

    fun onGetUserHealths(healthResponse: HealthResponse)

    fun onFailureResponse(failureResponse: FailureResponse)

    interface CallbackConnectorUser : CallbackConnector {

        fun onUserSignUp(authResponse: AuthResponse)

        fun onUserSignIn(authResponse: AuthResponse)

        fun onInsertHealth(healthResponse: HealthResponse)

        fun onGetHistoryCovidWorld(historyCovidResponse: HistoryCovidResponse)

        fun onGetHistoryCovidVn(historyCovidResponse: HistoryCovidResponse)

        fun onGetUserInformation(user: UserResponse)

        fun onUpdateUser(user: UserResponse)
    }

    interface CallbackConnectorAdmin : CallbackConnector {

        fun onGetAllUsers(listUsersResponse: ListUsersResponse)

        fun onDeleteUser(user: UserResponse)

        fun onLockUser(userResponse: UserResponse)
    }
}
