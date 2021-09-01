package com.example.giangnnh_advanceandroid_day10.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.example.giangnnh_advanceandroid_day10.databinding.ActivityMainBinding
import com.example.giangnnh_advanceandroid_day10.utils.Resource
import com.example.giangnnh_advanceandroid_day10.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
       initWeatherData()
        setContentView(binding.root)
    }

    private fun initWeatherData() {
        viewModel.getWeatherOfCity("Huế")
        viewModel.weatherDataLiveData.observe(this, {
            when (it) {
                is Resource.Success -> {
                    binding.weatherData = it.data
                    Log.e(TAG, "initWeatherData: ${it.data?.weather?.get(0)?.icon}", )
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