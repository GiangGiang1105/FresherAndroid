package com.example.connectorlibrary.enitity

@Suppress("UNUSED")
@Retention(AnnotationRetention.SOURCE)
annotation class ResponseCode {
    companion object {
        const val SUCCESS = 0
        const val ERROR_SIGN_UP = -1
        const val ERROR_SIGN_IN_USER_NOT_FOUND = -2
        const val ERROR_GET_USER = -3
        const val ERROR_UPDATE_USER = -4
        const val ERROR_GET_LIST_USERS = -5
        const val ERROR_INSERT_HEALTH = -6
        const val ERROR_HEALTHS = -7
        const val ERROR_LIST_SYMPTOMS_NULL = -8
        const val ERROR_GET_STATISTIC_COVID = -9
        const val ERROR_LIST_STATUS_NULL = -10
        const val ERROR_SIGN_UP_WITH_USER_EXISTS = -11
        const val ERROR_LIST_HEATHS_NOT_FOUND = -12
        const val ERROR_USER_NOT_FOUND = -13
        const val ERROR_LIST_USER_NULL = -14
        const val ERROR_DELETE_USER = -15
        const val ERROR_LOCK_USER = -16
        const val ERROR_LIST_GENDER_NULL = -17
        const val ERROR_STATISTIC_COVID_VN_NULL = -18
        const val ERROR_STATISTIC_COVID_WORLD_NULL = -19
        const val ERROR_HISTORY_COVID_VN_NULL = -20
        const val ERROR_HISTORY_COVID_WORLD_NULL = -21
    }
}