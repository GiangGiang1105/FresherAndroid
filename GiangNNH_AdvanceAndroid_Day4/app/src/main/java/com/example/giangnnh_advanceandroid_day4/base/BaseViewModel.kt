package com.example.giangnnh_advanceandroid_day4.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

 class BaseViewModel : ViewModel() {

    val a = 2

    var liveData: MutableLiveData<String> = MutableLiveData()

    fun setData(){
        liveData.postValue("hihiihi")
    }
    /*val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventError = MutableLiveData<Event<Resource.Error>>()

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showError(baseResponse: Resource.Error) {
        eventError.value = Event(baseResponse)
    }*/
}