package com.example.exercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.exercise2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainAdapter by lazy { MainAdapter() }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() {
        binding.linearRecyclerView.apply {
            adapter = mainAdapter
            setHasFixedSize(false)
        }
        binding.relativeRecyclerView.apply {
            adapter = mainAdapter
            setHasFixedSize(false)
        }
    }
}