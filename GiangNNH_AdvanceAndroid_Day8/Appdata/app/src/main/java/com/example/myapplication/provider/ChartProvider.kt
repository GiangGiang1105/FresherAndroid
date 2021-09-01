package com.example.myapplication.provider

import android.content.*
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.myapplication.data.dao.ChartDao
import com.example.myapplication.data.model.ColumnChart
import com.example.myapplication.data.model.PieChart
import com.example.myapplication.di.ContentProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors
import java.lang.IllegalArgumentException

class ChartProvider : ContentProvider() {

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(
            ChartContract.AUTHORITY,
            ChartContract.PATH_PIE_CHART,
            ChartContract.PieChartEntry.ALL_ITEMS
        )
        addURI(
            ChartContract.AUTHORITY,
            ChartContract.PATH_PIE_CHART + "/#",
            ChartContract.PieChartEntry.ONE_ITEM
        )
        addURI(
            ChartContract.AUTHORITY,
            ChartContract.PATH_COLUMN_CHART,
            ChartContract.ColumnChartEntry.ALL_ITEMS
        )
        addURI(
            ChartContract.AUTHORITY,
            ChartContract.PATH_COLUMN_CHART + "/#",
            ChartContract.ColumnChartEntry.ONE_ITEM
        )
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val chartItemDao = getDao(appContext = context?.applicationContext)
        val code = uriMatcher.match(uri)
        val cursor: Cursor?
        if (context == null) return null
        when (code) {
            ChartContract.ColumnChartEntry.ALL_ITEMS -> {
                cursor = chartItemDao.getAllColumnChartItem()
                cursor.setNotificationUri(context?.contentResolver, uri)
            }
            ChartContract.PieChartEntry.ALL_ITEMS -> {
                cursor = chartItemDao.getAllPieChartItem()
                cursor.setNotificationUri(context?.contentResolver, uri)
            }
            ChartContract.ColumnChartEntry.ONE_ITEM, ChartContract.PieChartEntry.ONE_ITEM -> throw IllegalArgumentException(
                "Invalid URI, cannot query with ID: $uri"
            )
            else -> throw IllegalArgumentException("Unknow URI: $uri")

        }
        return cursor
    }

    override fun getType(uri: Uri): String =
        when (uriMatcher.match(uri)) {
            ChartContract.PieChartEntry.ALL_ITEMS -> ChartContract.PieChartEntry.MIME_TYPE_DIR
            ChartContract.PieChartEntry.ONE_ITEM -> ChartContract.PieChartEntry.MIME_TYPE_ITEM
            ChartContract.ColumnChartEntry.ALL_ITEMS -> ChartContract.ColumnChartEntry.MIME_TYPE_DIR
            ChartContract.ColumnChartEntry.ONE_ITEM -> ChartContract.ColumnChartEntry.MIME_TYPE_ITEM
            else -> throw IllegalArgumentException("Unknow URI: $uri")
        }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val chartItemDao = getDao(appContext = context?.applicationContext)
        when (uriMatcher.match(uri)) {
            ChartContract.ColumnChartEntry.ALL_ITEMS -> {
                val id = chartItemDao.insertColumnChart(ColumnChart.fromContentValues(values))
                context?.contentResolver?.notifyChange(uri, null)
                return ContentUris.withAppendedId(uri, id)
            }
            ChartContract.PieChartEntry.ALL_ITEMS -> {
                val id = chartItemDao.insertPieChart(PieChart.fromContentValues(values))
                context?.contentResolver?.notifyChange(uri, null)
                return ContentUris.withAppendedId(uri, id)
            }
            ChartContract.PieChartEntry.ONE_ITEM, ChartContract.ColumnChartEntry.ONE_ITEM -> throw IllegalArgumentException(
                "Invalid URI, cannot insert with ID: $uri"
            )

            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw IllegalArgumentException("Invalid URI, cannot update with: $uri")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val chartItemDao = getDao(appContext = context?.applicationContext)
        val count: Int
        when (uriMatcher.match(uri)) {
            ChartContract.PieChartEntry.ALL_ITEMS -> {
                val pieChart = PieChart.fromContentValues(
                    values
                )
                count = chartItemDao.updatePieChart(
                    pieChart
                )
            }
            ChartContract.ColumnChartEntry.ALL_ITEMS -> {
                val columnChart = ColumnChart.fromContentValues(values)
                count = chartItemDao.updateColumnChart(
                    columnChart
                )
            }
            ChartContract.ColumnChartEntry.ONE_ITEM, ChartContract.PieChartEntry.ONE_ITEM -> throw IllegalArgumentException(
                "Invalid URI, cannot delete with ID: $uri"
            )
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    private fun getDao(appContext: Context?): ChartDao =
        EntryPointAccessors.fromApplication(appContext, ContentProviderEntryPoint::class.java)
            .getDao()
}