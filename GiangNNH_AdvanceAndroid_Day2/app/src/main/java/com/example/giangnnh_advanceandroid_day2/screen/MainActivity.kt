package com.example.giangnnh_advanceandroid_day2.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.giangnnh_advanceandroid_day2.R
import com.example.giangnnh_advanceandroid_day2.data.model.Film
import com.example.giangnnh_advanceandroid_day2.databinding.ActivityMainBinding
import com.example.giangnnh_advanceandroid_day2.listener.FindListener
import com.example.giangnnh_advanceandroid_day2.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), FindListener {

    private val listener: FindListener by lazy { this }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        findFilm()
    }

    private fun findFilm() {
        mainBinding.buttonPreview.setOnClickListener {
            mainViewModel.filterFilm(mainBinding.editText.text.toString().trim())
            listener.onListenerFindFilm()
            mainBinding.editText.text?.clear()
        }
    }

    override fun onListenerFindFilm() {
        mainViewModel.contentFilm.observe(this, {
            mainBinding.textView.text = it
        })
        mainViewModel.errorFilm.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}