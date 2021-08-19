package com.example.giangnnh_advanceandroid_day1.screen

import android.view.LayoutInflater
import android.widget.Toast
import com.example.giangnnh_advanceandroid_day1.R
import com.example.giangnnh_advanceandroid_day1.base.BaseActivity
import com.example.giangnnh_advanceandroid_day1.base.BaseRepository
import com.example.giangnnh_advanceandroid_day1.data.model.network.CountryApiService
import com.example.giangnnh_advanceandroid_day1.data.repository.CountryRepository
import com.example.giangnnh_advanceandroid_day1.databinding.ActivityMainBinding
import com.example.giangnnh_advanceandroid_day1.screen.adapter.CountryAdapter
import com.example.giangnnh_advanceandroid_day1.utils.Resource
import com.example.giangnnh_advanceandroid_day1.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding, BaseRepository>() {

    private val countryAdapter by lazy { CountryAdapter() }

    override fun init() {
        initView()
        updataCountry()
        clickItemCountry()
    }

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun getViewBinding(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun getRepository(): BaseRepository =
        CountryRepository.getInstance(param = countryRetrofit.retrofit(CountryApiService::class.java))

    private fun initView() {
        binding.countryRecyclerView.apply {
            adapter = countryAdapter
            setHasFixedSize(false)
        }
    }

    private fun updataCountry() {
        viewModel.fetchAllCountry()
        viewModel.listCountryLiveData.observe(this, {
            when (it) {
                is Resource.Success -> {
                    countryAdapter.updataMusic(it.data)
                }
                is Resource.Error -> {
                    if (it.isNetworkError) showToast(getString(R.string.error_network))
                    if (it.errorCode != null) showToast(getString(R.string.error_code + it.errorCode))
                    if (it.response != null) showToast(it.response.toString())
                }
            }
        })
    }

    private fun clickItemCountry() {
        countryAdapter.setOnItemClickListener {
            showToast(it.name + " -" + it.code)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}