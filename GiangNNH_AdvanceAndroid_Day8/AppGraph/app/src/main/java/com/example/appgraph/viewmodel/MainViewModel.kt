package com.example.appgraph.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgraph.app.MyApplication
import com.example.appgraph.data.model.ColumnChart
import com.example.appgraph.data.model.PieChart
import com.example.appgraph.data.repository.ChartRepository
import com.example.appgraph.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private var repository: ChartRepository) : ViewModel() {

    var pieChart: MutableLiveData<PieChart> = MutableLiveData()
        private set

    var columnChart: MutableLiveData<ColumnChart> = MutableLiveData()
        private set

    fun fetchValuesPieChart() = viewModelScope.launch {
        when (val value = repository.getValuesPieChart()) {
            is Resource.Success -> pieChart.value = value.data
            is Resource.Error -> MyApplication.logCatError(value.error.toString())
        }
    }

    fun fetchValuesColumnChart() = viewModelScope.launch {
        when (val value = repository.getValuesColumnChart()) {
            is Resource.Success -> columnChart.value = value.data
            is Resource.Error -> MyApplication.logCatError(value.error.toString())
        }
    }

    fun insertValuesPieChart(pieChart: PieChart) = viewModelScope.launch {
        when (val insertRespon = repository.insertValuesPieChart(pieChart)) {
            is Resource.Success -> MyApplication.logCatDebug("Insert piechart success")
            is Resource.Error -> MyApplication.logCatError(insertRespon.error.toString())
        }
    }

    fun insertValuesColumnChart(columnChart: ColumnChart) = viewModelScope.launch {
        when (val insertRespon = repository.insertValuesColumnChart(columnChart)) {
            is Resource.Success -> MyApplication.logCatDebug("Insert columnchart success")
            is Resource.Error -> MyApplication.logCatError(insertRespon.error.toString())
        }
    }

    fun updateValuesPieChart(pieChart: PieChart) = viewModelScope.launch {
        when (val updateRespon = repository.updateValuesPieChart(pieChart)) {
            is Resource.Success -> MyApplication.logCatDebug("Update piechat success")
            is Resource.Error -> MyApplication.logCatError(updateRespon.error.toString())
        }
    }

    fun updateValuesColumnChart(columnChart: ColumnChart) = viewModelScope.launch {
        when (val updateRespon = repository.updateValuesColumnChart(columnChart)) {
            is Resource.Success -> MyApplication.logCatDebug("Update column success")
            is Resource.Error -> MyApplication.logCatError(updateRespon.error.toString())
        }
    }
}