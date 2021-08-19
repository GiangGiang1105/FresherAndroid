package com.example.giangnnh_advanceandroid_day1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giangnnh_advanceandroid_day1.data.model.Country
import com.example.giangnnh_advanceandroid_day1.data.repository.CountryRepository
import com.example.giangnnh_advanceandroid_day1.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CountryRepository) : ViewModel() {

    private var _listCountry = MutableLiveData<Resource<List<Country>>>()

    val listCountryLiveData: LiveData<Resource<List<Country>>>
        get() = _listCountry

    fun fetchAllCountry () = viewModelScope.launch {
        _listCountry.value = repository.getAllCountry()
        Log.e("TAG", "fetchAllCountries: ${_listCountry.value}")
    }
}