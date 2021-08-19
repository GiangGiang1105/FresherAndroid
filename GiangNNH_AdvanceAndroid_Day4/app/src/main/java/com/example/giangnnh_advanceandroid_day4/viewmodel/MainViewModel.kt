package com.example.giangnnh_advanceandroid_day4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giangnnh_advanceandroid_day4.data.model.CategoryFood
import com.example.giangnnh_advanceandroid_day4.data.repository.CategoryRepository
import com.example.giangnnh_advanceandroid_day4.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    var category = MutableLiveData<CategoryFood>()
        private set
    var error = MutableLiveData<Resource.Error>()
        private set

    init {
        fetchAllCategories()
    }

    private fun fetchAllCategories() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().also {
                when (it) {
                    is Resource.Success -> category.postValue(it.data!!)
                    is Resource.Error -> error.postValue(it)
                }
            }
        }
    }
}