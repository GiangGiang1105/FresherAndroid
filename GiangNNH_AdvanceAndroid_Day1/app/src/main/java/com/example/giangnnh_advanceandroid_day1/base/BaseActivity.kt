package com.example.giangnnh_advanceandroid_day1.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.giangnnh_advanceandroid_day1.data.model.network.CountryRetrofit

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding, BR : BaseRepository> :
    AppCompatActivity() {

    lateinit var viewModel: VM
    protected lateinit var binding: VB
    protected val countryRetrofit: CountryRetrofit = CountryRetrofit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory(getRepository())).get(getViewModel())
        init()
    }

    abstract fun init()

    abstract fun getViewModel(): Class<VM>

    abstract fun getViewBinding(layoutInflater: LayoutInflater): VB

    abstract fun getRepository(): BR
}