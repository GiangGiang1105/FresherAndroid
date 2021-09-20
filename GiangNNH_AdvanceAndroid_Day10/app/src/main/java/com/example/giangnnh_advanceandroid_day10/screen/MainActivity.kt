package com.example.giangnnh_advanceandroid_day10.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.giangnnh_advanceandroid_day10.databinding.ActivityMainBinding
import com.example.giangnnh_advanceandroid_day10.utils.Resource
import com.example.giangnnh_advanceandroid_day10.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var listCities = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observerWeatherData()
        initSearchView()
        observerListCities()
    }

    private fun initSearchView() {
        binding.searchWeather.apply {
            addTextChangedListener {
                viewModel.getCitiesByNamePrefix(it.toString())
            }
            setOnItemClickListener { _, _, position, _ ->
                viewModel.getWeatherOfCity(listCities[position])
                clearFocus()
                clearListSelection()
            }
        }
    }


    private fun observerListCities() {
        viewModel.listCitiesLiveData.observe(this, {
            when (it) {
                is Resource.Success -> {
                    listCities = it.data?.data?.map { it.name } ?: listOf()
                    val adapterCities =
                        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listCities)
                    binding.searchWeather.apply {
                        setAdapter(adapterCities)
                        threshold = 1
                        showDropDown()
                    }
                }
                is Resource.Failure -> {
                    Log.e(TAG, "observerListCities Throw: ${it.throwable.peekContent().message}")
                }
                is Resource.Loading -> {
                }
                else -> throw IllegalAccessError("Error")
            }
        })
    }

    private fun observerWeatherData() {
        viewModel.getWeatherOfCity()
        viewModel.weatherDataLiveData.observe(this, {
            when (it) {
                is Resource.Success -> {
                    binding.weatherData = it.data
                }
                is Resource.Loading -> {
                    if (it.loading.getContentIfNotHandled() != null) {
                        if (it.loading.peekContent()) {
                            binding.processLoading.visibility = View.VISIBLE
                        } else {
                            binding.processLoading.visibility = View.GONE
                        }
                    }
                }
                is Resource.Failure -> {
                    if (it.throwable.getContentIfNotHandled() != null) {
                        Log.e(TAG, "initWeatherData: ${it.throwable.peekContent().message}")
                    }
                }
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity "
    }
}