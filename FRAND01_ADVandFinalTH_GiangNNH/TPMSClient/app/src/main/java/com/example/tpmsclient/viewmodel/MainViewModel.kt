package com.example.tpmsclient.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frand01_advandfinalth_giangnnh.data.model.TireDefault
import com.example.frand01_advandfinalth_giangnnh.data.model.TireSensor
import com.example.tpmsclient.data.repository.TireRepository
import com.example.tpmsclient.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TireRepository) : ViewModel() {

    var tireDefault: MutableLiveData<Resource<TireDefault>> = MutableLiveData()
        private set
    var resultInsertDefault: MutableLiveData<Resource<Long>> = MutableLiveData()
        private set
    var resultUpdateDefault: MutableLiveData<Resource<Int>> = MutableLiveData()
        private set
    var resultDeleteDefault: MutableLiveData<Resource<Int>> = MutableLiveData()
        private set

    var tireSensor: MutableLiveData<Resource<TireSensor>> = MutableLiveData()
        private set
    var resultInsertSensor: MutableLiveData<Resource<Long>> = MutableLiveData()
        private set
    var resultUpdateSensor: MutableLiveData<Resource<Int>> = MutableLiveData()
        private set
    var resultDeleteSensor: MutableLiveData<Resource<Int>> = MutableLiveData()
        private set


    fun connectServer() {
        repository.connectServer()
    }

    fun getTireDefault() = viewModelScope.launch {
        tireDefault.value = repository.getTireDefault()
    }

    fun getTireSensor() = viewModelScope.launch {
        tireSensor.value = repository.getTireSensor()
    }

    fun insertTireDefault(tireDefault: TireDefault) = viewModelScope.launch {
        resultInsertDefault.value = repository.insertTireDefault(tireDefault)
    }

    fun insertTireSensor(tireSensor: TireSensor) = viewModelScope.launch {
        resultInsertSensor.value = repository.insertTireSensor(tireSensor)
    }


    fun updateTireDefault(tireDefault: TireDefault) = viewModelScope.launch {
        resultUpdateDefault.value = repository.updateTireDefault(tireDefault)
    }

    fun updateTireSensor(tireSensor: TireSensor) = viewModelScope.launch {
        resultUpdateSensor.value = repository.updateTireSensor(tireSensor)
    }


    fun deleteTireDefault(tireDefault: TireDefault) = viewModelScope.launch {
        resultDeleteDefault.value = repository.deleteTireDefault(tireDefault)
    }

    fun deleteTireSensor(tireSensor: TireSensor) = viewModelScope.launch {
        resultDeleteSensor.value = repository.deleteTireSensor(tireSensor)
    }

    fun stopServerService() {
        repository.stopServerService()
    }
}