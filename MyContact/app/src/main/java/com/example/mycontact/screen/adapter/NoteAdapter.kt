package com.example.mycontact.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontact.R
import com.example.mycontact.data.database.entities.Note

class NoteAdapter : RecyclerView.Adapter<NoteHolder>() {

    private var listNotes = mutableListOf<Note>()

    fun loadNoteData(listNotes: MutableList<Note>) {
        listNotes.let {
            this.listNotes.clear()
            this.listNotes.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bindNoteData(listNotes[position])
    }

    override fun getItemCount() = listNotes.size
}