package com.example.myapplication.provider

import android.net.Uri

object ChartContract {
    private const val CONTENT_SCHEME = "content://"
    const val AUTHORITY = "com.example.myapplication.provider"
    const val PATH_PIE_CHART = "PieChart"
    const val PATH_COLUMN_CHART = "ColumnChart"

    object PieChartEntry {
        const val COLUMN_ID = "id"
        const val COLUMN_VALUE_1 = "value_1"
        const val COLUMN_VALUE_2 = "value_2"
        const val COLUMN_VALUE_3 = "value_3"
        const val COLUMN_VALUE_4 = "value_4"
        const val TABLE_NAME = "PieChart"
        val CONTENT_URI: Uri = Uri.parse("$CONTENT_SCHEME$AUTHORITY/$PATH_PIE_CHART")
        const val MIME_TYPE_DIR = "vnd.android.cursor.dir/vnd.$AUTHORITY.$TABLE_NAME"
        const val MIME_TYPE_ITEM = "vnd.android.cursor.item/vnd.$AUTHORITY.$TABLE_NAME"
        const val ALL_ITEMS = 1
        const val ONE_ITEM = 2
    }

    object ColumnChartEntry {
        const val TABLE_NAME = "ColumnChart"
        const val COLUMN_ID = "id"
        const val COLUMN_VALUE_1 = "value_1"
        const val COLUMN_VALUE_2 = "value_2"
        const val COLUMN_VALUE_3 = "value_3"
        const val COLUMN_VALUE_4 = "value_4"
        const val COLUMN_VALUE_5 = "value_5"
        val CONTENT_URI: Uri = Uri.parse("$CONTENT_SCHEME$AUTHORITY/$PATH_COLUMN_CHART")
        const val MIME_TYPE_DIR = "vnd.android.cursor.dir/vnd.$AUTHORITY.$TABLE_NAME"
        const val MIME_TYPE_ITEM = "vnd.android.cursor.item/vnd.$AUTHORITY.$TABLE_NAME"
        const val ALL_ITEMS = 3
        const val ONE_ITEM = 4
    }
}