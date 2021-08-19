package com.example.day6.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var controller: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding(layoutInflater)
        setContentView(binding.root)
        navHostFragment = getNavHostFragment()
        controller = navHostFragment.findNavController()
        init()
    }

    abstract fun getViewBinding(layoutInflater: LayoutInflater): VB

    abstract fun getNavHostFragment(): NavHostFragment

    abstract fun init()
}