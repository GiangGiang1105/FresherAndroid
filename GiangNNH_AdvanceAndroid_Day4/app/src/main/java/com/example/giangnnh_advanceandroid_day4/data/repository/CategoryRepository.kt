package com.example.giangnnh_advanceandroid_day4.data.repository

import com.example.giangnnh_advanceandroid_day4.base.BaseRepository
import com.example.giangnnh_advanceandroid_day4.data.network.ICategory
import com.example.giangnnh_advanceandroid_day4.utils.SingletonHolder

class CategoryRepository(private val iCategory: ICategory) : BaseRepository() {

    suspend fun getAllCategories() = safeApi {
        iCategory.getAllCategories()
    }

    companion object : SingletonHolder<CategoryRepository, ICategory>(::CategoryRepository)
}