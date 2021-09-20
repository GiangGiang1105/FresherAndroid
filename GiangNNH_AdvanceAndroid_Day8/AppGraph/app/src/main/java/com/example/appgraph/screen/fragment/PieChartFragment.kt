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
import com.example.appgraph.data.model.PieChart
import com.example.appgraph.databinding.FragmentPieChartBinding
import com.example.appgraph.viewmodel.MainViewModel
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PieChartFragment : Fragment() {

    private lateinit var binding: FragmentPieChartBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPieChartBinding.inflate(inflater)
        initData()
        handleSeekBar(binding.seekBarOne)
        handleSeekBar(binding.seekBarTwo)
        handleSeekBar(binding.seekBarThree)
        handleSeekBar(binding.seekBarFour)
        initColorView()
        return binding.root
    }

    private fun initData() {
        viewModel.fetchValuesPieChart()
        viewModel.pieChart.observe(viewLifecycleOwner, {
            if (it == null) viewModel.insertValuesPieChart(
                PieChart(
                    value_1 = 0.0,
                    value_2 = 0.0,
                    value_3 = 0.0,
                    value_4 = 0.0
                )
            )
            else {
                pieChart = it
                initBuildChart(pieChart)
                updateSeekBar(pieChart)
            }
        })
    }


    private fun updateSeekBar(pieChart: PieChart) {
        binding.seekBarOne.progress = pieChart.value_1.toInt()
        binding.seekBarTwo.progress = pieChart.value_2.toInt()
        binding.seekBarThree.progress = pieChart.value_3.toInt()
        binding.seekBarFour.progress = pieChart.value_4.toInt()
    }

    private fun initBuildChart(pieChart: PieChart) {
        val values = arrayListOf(
            PieEntry(calculatorPercent(pieChart.value_1), "Item 1", 0),
            PieEntry(calculatorPercent(pieChart.value_2), "Item 2", 1),
            PieEntry(calculatorPercent(pieChart.value_3), "Item 3", 2),
            PieEntry(calculatorPercent(pieChart.value_4), "Item 4", 3)
        )
        val dataSet = PieDataSet(values, "Pie Chart View").apply {
            setColors(*ColorTemplate.JOYFUL_COLORS)
        }
        val dataPie = PieData(dataSet).apply {
            setValueTextSize(16f)
            setValueTextColor(Color.BLUE)
            setValueFormatter(PercentFormatter())
        }
        binding.pieChart.apply {
            description = Description().apply {
                text = "Pie Chart"
            }
            data = dataPie
            isDrawHoleEnabled = false
            setUsePercentValues(true)
            invalidate()
        }

    }

    private fun initColorView() {
        binding.viewOne.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[0])
        binding.viewTwo.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[1])
        binding.viewThree.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[2])
        binding.viewFour.setBackgroundColor(ColorTemplate.JOYFUL_COLORS[3])
    }

    private fun handleSeekBar(seekBar: SeekBar) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (seekBar) {
                    binding.seekBarOne -> {
                        pieChart.value_1 = progress.toDouble()
                    }
                    binding.seekBarTwo -> {
                        pieChart.value_2 = progress.toDouble()
                    }
                    binding.seekBarThree -> {
                        pieChart.value_3 = progress.toDouble()
                    }
                    binding.seekBarFour -> {
                        pieChart.value_4 = progress.toDouble()
                    }
                }
                viewModel.updateValuesPieChart(pieChart)
                viewModel.fetchValuesPieChart()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun calculatorPercent(values: Double): Float =
        ((values / (pieChart.value_1 + pieChart.value_2 + pieChart.value_3 + pieChart.value_4)) * 100).toFloat()
}