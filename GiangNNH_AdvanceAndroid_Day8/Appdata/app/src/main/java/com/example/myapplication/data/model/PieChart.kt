package com.example.myapplication.data.model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.provider.ChartContract

@Entity(tableName = ChartContract.PieChartEntry.TABLE_NAME)
data class PieChart(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var value_1: Double = 0.0,
    var value_2: Double = 0.0,
    var value_3: Double = 0.0,
    var value_4: Double = 0.0,
) {
    companion object {

        fun fromContentValues(values: ContentValues?): PieChart? {
            val chartItem = PieChart()
            if (values != null) {
                if (values.containsKey(ChartContract.PieChartEntry.COLUMN_ID)) chartItem.id =
                    values.getAsInteger(ChartContract.PieChartEntry.COLUMN_ID)
                if (values.containsKey(ChartContract.PieChartEntry.COLUMN_VALUE_1)) chartItem.value_1 =
                    values.getAsDouble(ChartContract.PieChartEntry.COLUMN_VALUE_1)
                if (values.containsKey(ChartContract.PieChartEntry.COLUMN_VALUE_2)) chartItem.value_2 =
                    values.getAsDouble(
                        ChartContract.PieChartEntry.COLUMN_VALUE_2
                    )
                if (values.containsKey(ChartContract.PieChartEntry.COLUMN_VALUE_3)) chartItem.value_3 =
                    values.getAsDouble(
                        ChartContract.PieChartEntry.COLUMN_VALUE_3
                    )
                if (values.containsKey(ChartContract.PieChartEntry.COLUMN_VALUE_4)) chartItem.value_4 =
                    values.getAsDouble(
                        ChartContract.PieChartEntry.COLUMN_VALUE_4
                    )
                return chartItem
            }
            return null
        }
    }
}