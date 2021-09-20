package com.example.appgraph.data.repository

import android.net.Uri
import com.example.appgraph.base.BaseRepository
import com.example.appgraph.data.datasource.MyChartDataSource
import com.example.appgraph.data.model.ColumnChart
import com.example.appgraph.data.model.PieChart
import javax.inject.Inject

class ChartRepository @Inject constructor(private var myChartDataSource: MyChartDataSource) :
    BaseRepository() {

    suspend fun getValuesPieChart() = safeApi {
        myChartDataSource.getValuesPieChart()
    }

    suspend fun getValuesColumnChart() = safeApi {
        myChartDataSource.getValuesColumnChart()
    }

    suspend fun insertValuesPieChart(pieChart: PieChart) = safeApi {
        myChartDataSource.insertValuesPieChart(pieChart)
    }

    suspend fun insertValuesColumnChart(columnChart: ColumnChart) = safeApi {
        myChartDataSource.insertValuesColumnChart(columnChart)
    }

    suspend fun updateValuesPieChart(pieChart: PieChart) = safeApi {
        myChartDataSource.updateValuesPieChart(pieChart)
    }

    suspend fun updateValuesColumnChart(columnChart: ColumnChart) = safeApi {
        myChartDataSource.updateValuesColumnChart(columnChart)
    }
}