package com.example.exercise.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exercise.data.repository.MusicRepository
import com.example.exercise.screen.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(repository = repository as MusicRepository) as T
            else -> throw IllegalArgumentException("View Model not found!")
        }
    }
}