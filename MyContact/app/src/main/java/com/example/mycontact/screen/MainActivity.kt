package com.example.mycontact.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.mycontact.R
import com.example.mycontact.data.source.GetContact
import com.example.mycontact.screen.adapter.ContactAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val contactAdapter by lazy {
        ContactAdapter()
    }
    private val getContact by lazy {
        GetContact.getInstance(applicationContext?.contentResolver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        acceptPermission()
        buttonLoadContact.setOnClickListener {
            initData()
        }
        buttonNoteActivity.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initData() {

       /* GlobalScope.launch (Dispatchers.IO + handler) {
            try {
                contactAdapter.bindData(getContact.queryAllContact())
                val listContactsAsync = async { getContact.queryAllContact() }
                Log.e("RunBlock ", "$coroutineContext")
                withContext(Dispatchers.Main) {
                    val listContacts = listContactsAsync.await()
                    Log.e("RunBlock1111 ", "$coroutineContext")
                    contactAdapter.bindData(listContactsAsync.await())
                }
            } finally {

                Log.e("NGU", "ăn gì ngu thế?")
            }
        }*/
        MainScope().launch {
            contactAdapter.bindData(getContact.queryAllContact())
        }

    }

    private fun acceptPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.READ_CONTACTS),

            PERMISSIONS_REQUEST_READ_CONTACTS
        )
    }

    private fun initView() {
        contactRecyclerView.apply {
            adapter = contactAdapter

            setHasFixedSize(false)
        }
    }

    companion object {
        private const val PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    }
}
