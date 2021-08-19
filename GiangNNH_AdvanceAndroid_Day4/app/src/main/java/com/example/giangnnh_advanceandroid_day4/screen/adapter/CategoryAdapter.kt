package com.example.giangnnh_advanceandroid_day4.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.giangnnh_advanceandroid_day4.data.model.Category
import com.example.giangnnh_advanceandroid_day4.databinding.ItemCategoryBinding
import com.example.giangnnh_advanceandroid_day4.utils.DiffUtilsCallback

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private var _listCategories = mutableListOf<Category>()
    private var itemSetOnClickListener: ((category: Category) -> Unit)? = null

    fun setOnItemClickListener(itemOnClick: (category: Category) -> Unit) {
        itemSetOnClickListener = itemOnClick
    }

    fun updateListCategories(newListCategory: List<Category>?) {

        newListCategory?.let { newListCategory ->
            val diffResult: DiffUtil.DiffResult =
                DiffUtilsCallback(_listCategories, newListCategory).let {
                    DiffUtil.calculateDiff(it)
                }
            _listCategories.clear()
            _listCategories.addAll(newListCategory)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder =
        CategoryHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bindData(_listCategories[position], itemSetOnClickListener)
    }

    override fun getItemCount(): Int = _listCategories.size

    inner class CategoryHolder(private val itemCategoryBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemCategoryBinding.root) {

        fun bindData(mCategory: Category, onItemClick: ((category: Category) -> Unit)?) {
            itemCategoryBinding.apply {
                category = mCategory
                onClickListener = onItemClick
                executePendingBindings()
            }
        }
    }
}