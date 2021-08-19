package com.example.mycontact.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mycontact.R
import com.example.mycontact.data.database.entities.Note
import com.example.mycontact.screen.adapter.NoteAdapter
import com.example.mycontact.screen.viewmodel.NoteViewModel
import com.example.mycontact.screen.viewmodel.NoteViewModelFactory
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class NoteActivity : AppCompatActivity() {

    private val noteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModelFactory(context = application)
        ).get(NoteViewModel::class.java)
    }
    private val noteAdapter by lazy { NoteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        initView()
        initViewModel()
        loadData()
        insertData()
    }

    private fun initViewModel() {

    }

    private fun initView() {
        noteRecyclerView.apply {
            setHasFixedSize(false)
            adapter = noteAdapter
        }
    }

    private fun loadData() {
        lifecycleScope.launchWhenStarted {
            noteViewModel.stateFlowNotes.collect {
                noteAdapter.loadNoteData(it)
            }
        }
    }

    private fun insertData() {
        buttonNote.setOnClickListener {
            runBlocking {
                withContext(Dispatchers.IO) {
                    noteViewModel.insertNote(
                        Note(
                            title = editTextTitle.text.toString(),
                            content = editTextContent.text.toString()
                        )
                    )
                }
            }
        }
    }
}