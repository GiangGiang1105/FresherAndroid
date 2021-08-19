package com.example.giangnnh_advanceandroid_day4.utils

open class SingletonHolder<out T, in A>(creator: (A) -> T) {

    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun getInstance(param: A): T {
        val checkInstance = instance
        if (checkInstance != null) return checkInstance
        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) checkInstanceAgain
            else {
                val created = creator!!(param)
                instance = created
                creator = null
                created
            }
        }
    }
}