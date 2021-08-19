package com.example.giangnnh_advanceandroid_day2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giangnnh_advanceandroid_day2.data.local.ManagerFilm
import com.example.giangnnh_advanceandroid_day2.data.model.Film
import com.example.giangnnh_advanceandroid_day2.data.repository.FilmRepository

class MainViewModel : ViewModel() {

    private var _contentFilm: MutableLiveData<String> = MutableLiveData()
    val contentFilm: LiveData<String>
        get() = _contentFilm

    private var _errorFilm: MutableLiveData<String> = MutableLiveData()
    val errorFilm: LiveData<String>
        get() = _errorFilm

    private val repository by lazy { FilmRepository.getInstance(ManagerFilm()) }

    fun filterFilm(name: String) {
        val listFilms = repository.getAllFilms()
        val filmFinded: Film? = listFilms.find {
            it.name == name
        }
        if (filmFinded != null)
            _contentFilm.value = filmFinded?.content
        else _errorFilm.value = "Not find name film :$name"
    }
}