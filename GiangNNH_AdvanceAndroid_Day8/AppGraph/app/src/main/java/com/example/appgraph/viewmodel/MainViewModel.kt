package com.example.appgraph.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgraph.data.model.ColumnChart
import com.example.appgraph.data.model.PieChart
import com.example.appgraph.data.repository.ChartRepository
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
        pieChart.value = repository.getValuesPieChart()
    }

    fun fetchValuesColumnChart() = viewModelScope.launch {
        columnChart.value = repository.getValuesColumnChart()
    }

    fun insertValuesPieChart(pieChart: PieChart) = viewModelScope.launch {
        repository.insertValuesPieChart(pieChart)
    }

    fun insertValuesColumnChart(columnChart: ColumnChart) = viewModelScope.launch {
        repository.insertValuesColumnChart(columnChart)
    }

    fun updateValuesPieChart(pieChart: PieChart) = viewModelScope.launch {
        repository.updateValuesPieChart(pieChart)
    }

    fun updateValuesColumnChart(columnChart: ColumnChart) = viewModelScope.launch {
        repository.updateValuesColumnChart(columnChart)
    }
}