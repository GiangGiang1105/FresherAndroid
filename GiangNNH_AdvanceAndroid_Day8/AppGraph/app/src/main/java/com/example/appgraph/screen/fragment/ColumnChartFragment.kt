package com.example.appgraph.screen.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import com.example.appgraph.data.model.ColumnChart
import com.example.appgraph.databinding.FragmentColumnChartBinding
import com.example.appgraph.viewmodel.MainViewModel
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColumnChartFragment : Fragment() {

    private lateinit var binding: FragmentColumnChartBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var columnChart: ColumnChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColumnChartBinding.inflate(inflater)
        initData()
        handleSeekBar(binding.seekBarOne)
        handleSeekBar(binding.seekBarTwo)
        handleSeekBar(binding.seekBarThree)
        handleSeekBar(binding.seekBarFour)
        handleSeekBar(binding.seekBarFive)
        initColorView()
        return binding.root
    }

    private fun initData() {
        viewModel.fetchValuesColumnChart()
        viewModel.columnChart.observe(viewLifecycleOwner, {
            if (it == null) viewModel.insertValuesColumnChart(
                ColumnChart(
                    value_1 = 0.0,
                    value_2 = 0.0,
                    value_3 = 0.0,
                    value_4 = 0.0,
                    value_5 = 0.0
                )
            )
            else {
                columnChart = it
                initBuildChart(columnChart)
                updateSeekBar(columnChart)
            }
        })
    }

    private fun updateSeekBar(columnChart: ColumnChart) {
        binding.seekBarOne.progress = columnChart.value_1.toInt()
        binding.seekBarTwo.progress = columnChart.value_2.toInt()
        binding.seekBarThree.progress = columnChart.value_3.toInt()
        binding.seekBarFour.progress = columnChart.value_4.toInt()
        binding.seekBarFive.progress = columnChart.value_5.toInt()
    }

    private fun initBuildChart(columnChart: ColumnChart) {
        val barDataSet = BarDataSet(
            listOf(
                BarEntry(0f, columnChart.value_1.toFloat()),
                BarEntry(1f, columnChart.value_2.toFloat()),
                BarEntry(2f, columnChart.value_3.toFloat()),
                BarEntry(3f, columnChart.value_4.toFloat()),
                BarEntry(4f, columnChart.value_5.toFloat())
            ), "Column Chart View"
        ).apply {
            setColors(*ColorTemplate.JOYFUL_COLORS)
            setValueTextColors(listOf(Color.GREEN))
            valueTextSize = 12f
        }
        val barData = BarData(barDataSet)
        val labels = listOf("Item1", "Item2", "Item3", "Item4", "Item5")
        binding.barChart.apply {
            data = barData
            setDrawBarShadow(false)
            setDrawGridBackground(false)
            description = Description().apply {
                text = ""
            }
            animateY(100)
            animateX(100)
            axisRight.isEnabled = false
            axisLeft.apply {
                axisMinimum = 0f
                axisMaximum = 100f
            }
            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(labels)
                position = XAxis.XAxisPosition.BOTTOM
                setDrawAxisLine(false)
                setDrawGridLines(false)
            }
            setFitBars(true)
            invalidate()
        }
    }

    private fun initColorView(){
        binding.viewOne.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[0])
        binding.viewTwo.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[1])
        binding.viewThree.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[2])
        binding.viewFour.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[3])
        binding.viewFive.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[4])
    }

    private fun handleSeekBar(seekBar: SeekBar) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (seekBar) {
                    binding.seekBarOne -> {
                        columnChart.value_1 = progress.toDouble()
                    }
                    binding.seekBarTwo -> {
                        columnChart.value_2 = progress.toDouble()
                    }
                    binding.seekBarThree -> {
                        columnChart.value_3 = progress.toDouble()
                    }
                    binding.seekBarFour -> {
                        columnChart.value_4 = progress.toDouble()
                    }
                    binding.seekBarFive -> {
                        columnChart.value_5 = progress.toDouble()
                    }
                }
                viewModel.updateValuesColumnChart(columnChart)
                viewModel.fetchValuesColumnChart()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
