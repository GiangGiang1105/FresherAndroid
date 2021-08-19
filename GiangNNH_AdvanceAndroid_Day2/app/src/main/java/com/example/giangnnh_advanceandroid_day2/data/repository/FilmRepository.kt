package com.example.giangnnh_advanceandroid_day2.data.repository

import com.example.giangnnh_advanceandroid_day2.data.local.ManagerFilm

class FilmRepository(private val managerFilm: ManagerFilm) {

    fun getAllFilms() = managerFilm.getAllFilm()

    companion object {
        private var instance: FilmRepository? = null

        fun getInstance(managerFilm: ManagerFilm) = instance ?: FilmRepository(managerFilm).also {
            instance = it
        }
    }
}