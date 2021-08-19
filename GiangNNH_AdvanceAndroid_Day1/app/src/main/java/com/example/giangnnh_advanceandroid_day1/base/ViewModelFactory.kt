package com.example.giangnnh_advanceandroid_day1.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giangnnh_advanceandroid_day1.data.repository.CountryRepository
import com.example.giangnnh_advanceandroid_day1.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(repository = repository as CountryRepository) as T
            else -> throw IllegalArgumentException("View Model not found!")
        }
    }
}