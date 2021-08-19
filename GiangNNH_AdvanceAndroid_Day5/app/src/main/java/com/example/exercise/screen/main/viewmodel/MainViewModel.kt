package com.example.exercise.screen.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise.data.model.Music
import com.example.exercise.data.repository.MusicRepository
import com.example.exercise.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MusicRepository) : ViewModel() {

    private var _listMusic = MutableLiveData<Resource<List<Music>>>()

    val listMusicLiveData: LiveData<Resource<List<Music>>>
        get() = _listMusic

    fun getAllMusic() = viewModelScope.launch {
        _listMusic.value = repository.getAllMusic()
    }
}