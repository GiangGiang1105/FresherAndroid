package com.example.appgraph.data.datasource

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.appgraph.data.model.ColumnChart
import com.example.appgraph.data.model.PieChart
import com.example.appgraph.utils.ChartContract

class MyChartDataSource(context: Context) {

    private val contentResolver by lazy { context.contentResolver }

    @SuppressLint("Recycle")
    fun getValuesPieChart(): PieChart? {
        val result = PieChart()
        val cursor = contentResolver.query(
            ChartContract.PieChartEntry.CONTENT_URI,
            arrayOf(
                ChartContract.PieChartEntry.COLUMN_ID,
                ChartContract.PieChartEntry.COLUMN_VALUE_1,
                ChartContract.PieChartEntry.COLUMN_VALUE_2,
                ChartContract.PieChartEntry.COLUMN_VALUE_3,
                ChartContract.PieChartEntry.COLUMN_VALUE_4
            ),
            null,
            null,
            null
        )
        if (cursor?.moveToNext() == true) {
            cursor.apply {
                result.id = getInt(getColumnIndex(ChartContract.PieChartEntry.COLUMN_ID))
                result.value_1 =
                    getDouble(getColumnIndex(ChartContract.PieChartEntry.COLUMN_VALUE_1))
                result.value_2 =
                    getDouble(getColumnIndex(ChartContract.PieChartEntry.COLUMN_VALUE_2))
                result.value_3 =
                    getDouble(getColumnIndex(ChartContract.PieChartEntry.COLUMN_VALUE_3))
                result.value_4 =
                    getDouble(getColumnIndex(ChartContract.PieChartEntry.COLUMN_VALUE_4))
                close()
            }
            return result
        }
        return null
    }

    @SuppressLint("Recycle")
    fun getValuesColumnChart(): ColumnChart? {
        val result = ColumnChart()
        val cursor = contentResolver.query(
            ChartContract.ColumnChartEntry.CONTENT_URI,
            arrayOf(
                ChartContract.ColumnChartEntry.COLUMN_ID,
                ChartContract.ColumnChartEntry.COLUMN_VALUE_1,
                ChartContract.ColumnChartEntry.COLUMN_VALUE_2,
                ChartContract.ColumnChartEntry.COLUMN_VALUE_3,
                ChartContract.ColumnChartEntry.COLUMN_VALUE_4,
                ChartContract.ColumnChartEntry.COLUMN_VALUE_5
            ),
            null,
            null,
            null
        )
        if (cursor?.moveToNext() == true) {
            cursor.apply {
                result.id = getInt(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_ID))
                result.value_1 =
                    getDouble(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_VALUE_1))
                result.value_2 =
                    getDouble(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_VALUE_2))
                result.value_3 =
                    getDouble(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_VALUE_3))
                result.value_4 =
                    getDouble(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_VALUE_4))
                result.value_5 =
                    getDouble(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_VALUE_5))
            }
            return result
        }
        return null
    }

    fun insertValuesPieChart(pieChart: PieChart): Uri? {
        val values = ContentValues().apply {
            put(ChartContract.PieChartEntry.COLUMN_VALUE_1, pieChart.value_1)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_2, pieChart.value_2)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_3, pieChart.value_3)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_4, pieChart.value_4)
        }
        return contentResolver.insert(ChartContract.PieChartEntry.CONTENT_URI, values)
    }

    fun insertValuesColumnChart(columnChart: ColumnChart): Uri? {
        val values = ContentValues().apply {
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_1, columnChart.value_1)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_2, columnChart.value_2)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_3, columnChart.value_3)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_4, columnChart.value_4)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_5, columnChart.value_5)
        }
        return contentResolver.insert(ChartContract.ColumnChartEntry.CONTENT_URI, values)
    }

    fun updateValuesPieChart(pieChart: PieChart): Int {
        val values = ContentValues().apply {
            put(ChartContract.PieChartEntry.COLUMN_ID, pieChart.id)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_1, pieChart.value_1)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_2, pieChart.value_2)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_3, pieChart.value_3)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_4, pieChart.value_4)
        }
        return contentResolver.update(ChartContract.PieChartEntry.CONTENT_URI, values, null, null)
    }

    fun updateValuesColumnChart(columnChart: ColumnChart): Int {
        val values = ContentValues().apply {
            put(ChartContract.ColumnChartEntry.COLUMN_ID, columnChart.id)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_1, columnChart.value_1)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_2, columnChart.value_2)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_3, columnChart.value_3)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_4, columnChart.value_4)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_5, columnChart.value_5)
        }
        return contentResolver.update(
            ChartContract.ColumnChartEntry.CONTENT_URI,
            values,
            null,
            null
        )
    }
}