package com.example.mycontact.data.source

import android.app.Application
import com.example.mycontact.data.database.NoteDatabase
import com.example.mycontact.data.database.dao.NoteDao
import com.example.mycontact.data.database.entities.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository private constructor(context: Application) {

    private var noteDao: NoteDao
    private var stateFlowListNotes : Flow<MutableList<Note>>

    init {
        val database = NoteDatabase.getInstance(context)
        noteDao = database.getNoteDao()
        stateFlowListNotes = noteDao.getNote()
    }

    fun getAllNotes(): Flow<MutableList<Note>> = stateFlowListNotes

    fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    companion object {
        private var instance: NoteRepository? = null

        fun getInstance(context: Application) = instance ?: NoteRepository(context).also {
            instance = it
        }
    }
}