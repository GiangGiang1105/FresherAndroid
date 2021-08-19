package com.example.exercise.screen.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise.data.model.Music
import com.example.exercise.databinding.ItemMusicBinding

class MusicAdapter() : RecyclerView.Adapter<MusicAdapter.MusicHolder>() {

    private var itemOnClickView: ((music: Music) -> Unit?)? = null

    private val diffCallback = object : DiffUtil.ItemCallback<Music>() {
        override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean =
            oldItem == newItem

    }

    val mDiffer = AsyncListDiffer(this, diffCallback)

    fun setOnItemClickListener(itemClick: (music: Music) -> Unit) {
        itemOnClickView = itemClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MusicHolder(
            ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.bindData(mDiffer.currentList[position])
        holder.itemView.setOnClickListener {
            itemOnClickView?.invoke(mDiffer.currentList[position])
        }
    }

    override fun getItemCount() = mDiffer.currentList.size

    class MusicHolder(
        private var itemMusicBinding: ItemMusicBinding
    ) :
        RecyclerView.ViewHolder(itemMusicBinding.root) {

        fun bindData(music: Music) {
            itemMusicBinding.music = music
        }
    }
}