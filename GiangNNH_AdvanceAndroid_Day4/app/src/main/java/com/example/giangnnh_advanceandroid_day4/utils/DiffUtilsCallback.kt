package com.example.giangnnh_advanceandroid_day4.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.giangnnh_advanceandroid_day4.data.model.Category

class DiffUtilsCallback(
    private val oldCategories: List<Category>,
    private val newCategories: List<Category>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldCategories.size

    override fun getNewListSize(): Int = newCategories.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldCategories[oldItemPosition].idCategory == newCategories[newItemPosition].idCategory

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldCategories[oldItemPosition] == newCategories[newItemPosition]
}