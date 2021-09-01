package com.example.appgraph.data.repository

import android.net.Uri
import com.example.appgraph.base.BaseRepository
import com.example.appgraph.data.datasource.MyChartDataSource
import com.example.appgraph.data.model.ColumnChart
import com.example.appgraph.data.model.PieChart
import javax.inject.Inject

class ChartRepository @Inject constructor(private var myChartDataSource: MyChartDataSource) :
    BaseRepository() {

    suspend fun getValuesPieChart(): PieChart? = safeApi {
        myChartDataSource.getValuesPieChart()
    }

    suspend fun getValuesColumnChart(): ColumnChart? = safeApi {
        myChartDataSource.getValuesColumnChart()
    }

    suspend fun insertValuesPieChart(pieChart: PieChart): Uri? = safeApi {
        myChartDataSource.insertValuesPieChart(pieChart)
    }

    suspend fun insertValuesColumnChart(columnChart: ColumnChart): Uri? = safeApi {
        myChartDataSource.insertValuesColumnChart(columnChart)
    }

    suspend fun updateValuesPieChart(pieChart: PieChart): Int = safeApi {
        myChartDataSource.updateValuesPieChart(pieChart)
    }

    suspend fun updateValuesColumnChart(columnChart: ColumnChart): Int = safeApi {
        myChartDataSource.updateValuesColumnChart(columnChart)
    }
}