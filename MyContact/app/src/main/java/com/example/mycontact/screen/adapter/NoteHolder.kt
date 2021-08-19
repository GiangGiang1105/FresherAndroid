package com.example.mycontact.screen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontact.data.database.entities.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindNoteData(note: Note) {
        itemView.apply {
            itemView.textContentItem.text = note.content
            itemView.textTitleItem.text = note.title
        }
    }
}