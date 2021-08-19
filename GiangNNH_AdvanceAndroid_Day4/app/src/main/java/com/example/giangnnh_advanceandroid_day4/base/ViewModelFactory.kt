package com.example.giangnnh_advanceandroid_day4.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giangnnh_advanceandroid_day4.data.repository.CategoryRepository
import com.example.giangnnh_advanceandroid_day4.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(repository as CategoryRepository) as T
            else -> throw IllegalArgumentException("Not Found MainViewModel")
        }
    }
}