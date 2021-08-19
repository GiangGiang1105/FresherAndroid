package com.example.mycontact.screen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontact.data.database.entities.Note
import com.example.mycontact.data.source.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NoteViewModel(context: Application) : ViewModel() {

    private var noteRepository = NoteRepository.getInstance(context)

    var stateFlowNotes: MutableStateFlow<MutableList<Note>> = MutableStateFlow(
        mutableListOf()
    )

    init {
        viewModelScope.launch {
            noteRepository.getAllNotes().collect {
                stateFlowNotes.value = it
            }
        }
    }

    fun insertNote(note: Note) {
        noteRepository.insert(note)
    }
}