package com.example.exercise2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise2.databinding.ItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.Holder>() {

    private val listName = listOf(
        "Nguyễn Ngọc Hà Giang",
        " Nguyễn Thị Tiến",
        "Trần Đình Nam",
        "Lê Văn Phóng",
        "Lê Đình Văn",
        "Trần Đình Hoàn",
        "Nguyễn Ngọc Hà Giang",
        " Nguyễn Thị Tiến",
        "Trần Đình Nam",
        "Lê Văn Phóng",
        "Lê Đình Văn",
        "Trần Đình Hoàn"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(listName[position])
    }

    override fun getItemCount() = listName.size

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(name: String) {
            val binding = ItemBinding.bind(itemView)
            binding.textName.text = name
        }
    }

}